package controller;

import view.InputView;

public class GameController {
    private final InputView inputView;

    public GameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        inputView.readPlayerName();
    }
}
