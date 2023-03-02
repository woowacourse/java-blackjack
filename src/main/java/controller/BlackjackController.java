package controller;

import domain.Players;
import view.InputView;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Players players = Players.from(inputView.readNames());

    }
}
