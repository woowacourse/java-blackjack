package blackjack;

import static blackjack.view.InputView.requestPlayerNamesInput;

import blackjack.controller.BlackjackController;

public class Application {
    private static final BlackjackController blackjackController = new BlackjackController();

    public static void main(String[] args) {
        blackjackController.initializeGame(requestPlayerNamesInput());
    }
}
