package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.game.BettingSession;
import domain.game.Game;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.shuffler.RandomShuffler;
import java.util.List;
import java.util.function.Supplier;
import view.Answer;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Game game = retryUntilSuccess(this::startGame);
        BettingSession bettingSession = startBetting();
        bet(game, bettingSession);
        outputView.displayInitialDeal(game);

        dealAdditionalCardsToPlayers(game);
        dealAdditionalCardsToDealer(game);

        outputView.displayScore(game);
        displayAllProfits(game, bettingSession);
    }

    private Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, new Deck(new RandomShuffler()));
    }

    private BettingSession startBetting() {
        return new BettingSession();
    }

    private void bet(Game game, BettingSession bettingSession) {
        game.getPlayers().forEach(player -> {
            int betAmount = inputView.readBetAmount(player.getName());
            bettingSession.bet(player, betAmount);
        });
    }

    private void dealAdditionalCardsToPlayers(Game game) {
        game.getPlayerNames()
                .forEach((name) -> hitOrStay(game, name));
    }

    private void hitOrStay(Game game, String playerName) {
        while (game.canHit(playerName)) {
            Answer answer = retryUntilSuccess(() -> inputView.readHitOrStay(playerName));
            if (answer == Answer.NO) {
                displayPlayerCards(game, playerName);
                return;
            }
            game.hit(playerName);
            displayPlayerCards(game, playerName);
        }
    }

    private void displayPlayerCards(Game game, String playerName) {
        List<Card> cards = game.getCardsOf(playerName);
        outputView.displayNameAndCards(playerName, cards);
        outputView.displayEmptyLine();
    }

    private void dealAdditionalCardsToDealer(Game game) {
        outputView.displayEmptyLine();
        while (game.canHit(Dealer.NAME)) {
            game.hit(Dealer.NAME);
            outputView.displayDealerHitMessage();
        }
        outputView.displayEmptyLine();
    }

    private void displayAllProfits(Game game, BettingSession bettingSession) {
        List<Player> players = game.getPlayers();
        bettingSession.calculateProfit(players, game.getDealer());

        outputView.displayProfitMessage();
        displayDealerProfit(bettingSession);
        displayPlayersProfit(players, bettingSession);
    }

    private void displayDealerProfit(BettingSession bettingSession) {
        outputView.displayParticipantProfit(Dealer.NAME, bettingSession.getDealerProfit());
    }

    private void displayPlayersProfit(List<Player> players, BettingSession bettingSession) {
        for (Player player : players) {
            double profit = bettingSession.getPlayerProfit(player);
            outputView.displayParticipantProfit(player.getName(), profit);
        }
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.printf("%s%n", e.getMessage());
            return retryUntilSuccess(supplier);
        }
    }
}
