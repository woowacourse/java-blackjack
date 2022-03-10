package blackjack;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
import static blackjack.view.OutputView.printDealerExtraCardInfo;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printInitialParticipantsCards;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Player;
import java.util.List;

public class Application {

    private static final BlackjackController blackjackController = new BlackjackController();

    public static void main(String[] args) {
        BlackjackGame blackjackGame = blackjackController.initializeGame(requestPlayerNamesInput());
        printInitialParticipantsCards(blackjackGame);

        List<Player> players = blackjackGame.getParticipants();
        for (Player player : players) {
            blackjackController.givePlayerCards(player, blackjackGame);
        }

        if (blackjackGame.giveCardToDealer()) {
            printDealerExtraCardInfo();
        }

        printAllCardsAndScore(blackjackGame);

        printGameResult(blackjackController.finishGame(blackjackGame));
    }
}
