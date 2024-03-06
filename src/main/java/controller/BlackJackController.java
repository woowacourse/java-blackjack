package controller;

import domain.Players;
import view.InputView;

public class BlackJackController {
    private final InputView inputView;

    public BlackJackController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Players players = Players.from(inputView.readPlayerNames());
    }
}
