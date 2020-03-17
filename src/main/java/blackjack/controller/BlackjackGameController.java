package blackjack.controller;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.user.*;
import blackjack.utils.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackGameController {
    public static void run() {
        BlackjackGame game = new BlackjackGame(
                enrollPlayers(),
                new Deck(CardFactory.getInstance().issueNewDeck()));
        game.distributeInitialCards();
        OutputView.printInitialCardDistribution(game);
        hitMoreCard(game.getPlayers(), game);
        dealerHitsAdditionalCard(game);
        OutputView.printFinalCardScore(game);
        OutputView.printFinalResult(game.calculateResultsPerPlayer(), game.calculateTotalResultCount());
    }

    private static List<Player> enrollPlayers() {
        return PlayerFactory.generatePlayers(
                InputHandler.parseName(InputView.inputPlayerName())
        );
    }

    private static void hitMoreCard(List<Player> players, BlackjackGame game) {
        players.forEach(player -> askForHit(player, game));
    }

    private static void askForHit(Player player, BlackjackGame game) {
        while (InputView.askForHit(player.getName())) {
            game.hitCard(player);
            OutputView.printUserStatus(player);
            if (player.isBusted()) {
                OutputView.printBusted(player.getName());
                break;
            }
        }
    }

    private static void dealerHitsAdditionalCard(BlackjackGame game) {
        while(game.dealerHitsAdditionalCard()) {
            OutputView.printDealerHitMoreCard();
        }
    }
}
