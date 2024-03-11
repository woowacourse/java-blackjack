package model.controller;

import model.player.Players;
import model.view.InputView;

public class BlackjackGameController {

    private final InputView inputView;

    public BlackjackGameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Players players = inputView.requestPlayersName();
    }
}
