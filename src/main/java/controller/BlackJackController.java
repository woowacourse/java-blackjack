package controller;

import domain.card.Card;
import domain.card.Deck;
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
        bet(game);
        outputView.displayInitialDeal(game);

        dealAdditionalCardsToPlayers(game);
        dealAdditionalCardsToDealer(game);

        outputView.displayScore(game);
        displayAllProfit(game);
    }

    private Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, new Deck(new RandomShuffler()));
    }

    private void bet(Game game) {
        game.getPlayerNames().forEach(name -> {
            int betAmount = inputView.readBetAmount(name);
            game.bet(name, betAmount);
        });
    }

    private void dealAdditionalCardsToPlayers(Game game) {
        for (String name : game.getPlayerNames()) {
            hitOrStay(game, name);
        }
    }

    private void hitOrStay(Game game, String playerName) {
        while (game.canPlayerHit(playerName)) {
            Answer answer = retryUntilSuccess(() -> inputView.readHitOrStay(playerName));
            if (answer == Answer.NO) {
                displayPlayerCards(game, playerName);
                return;
            }
            game.playerHit(playerName);
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
        while (game.canDealerHit()) {
            game.dealerHit();
            outputView.displayDealerHitMessage();
        }
        outputView.displayEmptyLine();
    }

    private void displayAllProfit(Game game) {
        outputView.displayProfitMessage();
        displayDealerProfit(game);
        displayPlayersProfit(game);
    }

    private void displayDealerProfit(Game game) {
        outputView.displayParticipantProfit(Dealer.NAME, game.getDealerProfit());
    }

    private void displayPlayersProfit(Game game) {
        List<Player> players = game.getPlayers();
        Dealer dealer = game.getDealer();
        for (Player player : players) {
            outputView.displayParticipantProfit(player.getName(), player.calculateProfit(dealer));
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
