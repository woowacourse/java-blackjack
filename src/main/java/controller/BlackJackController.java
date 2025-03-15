package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.game.BettingSession;
import domain.game.Game;
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
        giveAdditionalCardsForPlayer(game);
        giveAdditionalCardsForDealer(game);
        displayScores(game);
        calculateAndDisplayBettingProfit(game, bettingSession);
    }

    public Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, new Deck(new RandomShuffler()));
    }

    public BettingSession startBetting() {
        return new BettingSession();
    }

    private void giveAdditionalCardsForPlayer(Game game) {
        game.getPlayerNames()
                .forEach((name) -> hitOrStay(game, name));
    }

    private void giveAdditionalCardsForDealer(Game game) {
        outputView.displayEmptyLine();
        while (game.doesDealerNeedCard()) {
            game.dealerHit();
            outputView.displayDealerHitResult();
        }
        outputView.displayEmptyLine();
    }

    private void displayScores(Game game) {
        outputView.displayScore(game);
    }

    private void hitOrStay(Game game, String playerName) {
        Answer answer = Answer.YES;
        while (game.canHit(playerName) && answer == Answer.YES) {
            answer = retryUntilSuccess(() -> inputView.readHitOrStay(playerName));
            hitByAnswer(game, playerName, answer);
            displayPlayerCards(game, playerName);
        }
    }

    private void hitByAnswer(Game game, String playerName, Answer answer) {
        if (answer == Answer.YES) {
            game.playerHit(playerName);
        }
    }

    private void displayPlayerCards(Game game, String playerName) {
        List<Card> cards = game.getPlayerCards(playerName);
        outputView.displayNameAndCards(playerName, cards);
        outputView.displayEmptyLine();
    }

    public void bet(Game game, BettingSession bettingSession) {
        List<Player> players = game.getPlayers();
        players.forEach(player -> playerBet(bettingSession, player));
    }

    public void playerBet(BettingSession bettingSession, Player player) {
        int betAmount = inputView.readBetAmount(player.getName());
        bettingSession.bet(player, betAmount);
    }

    private void calculateAndDisplayBettingProfit(Game game, BettingSession bettingSession) {
        List<Player> players = game.getPlayers();
        bettingSession.calculateProfit(players, game.getDealer());
        outputView.displayProfitMessage();
        outputView.displayParticipantProfit("딜러", bettingSession.getDealerProfit());
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
