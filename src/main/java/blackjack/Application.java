package blackjack;

import static blackjack.view.InputView.requestPlayerNamesInput;
import static blackjack.view.OutputView.printInitialParticipantsCards;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;

public class Application {
    private static final BlackjackController blackjackController = new BlackjackController();

    public static void main(String[] args) {
        BlackjackGame blackjackGame = blackjackController.initializeGame(requestPlayerNamesInput());
        printInitialParticipantsCards(blackjackGame);
    }
}
