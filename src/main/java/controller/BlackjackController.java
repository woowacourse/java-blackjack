package controller;

import domain.BlackjackGame;
import view.InputView;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.initialize(inputView.readNames());
    }
}
