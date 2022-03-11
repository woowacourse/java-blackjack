package blackjack;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printAllCardsAndScore;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printInitialParticipantsCards;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;

public class Application {

    private static final BlackjackController controller = new BlackjackController();

    public static void main(String[] args) {
        BlackjackGame game = controller.initializeGame(requestPlayerNamesInput());
        printInitialParticipantsCards(controller.getInitialDistribution(game));

        controller.distributeAllCards(game);
        controller.getGameResult(game);
    }
}
