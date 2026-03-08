package controller;

import domain.player.PlayerParser;
import domain.player.Players;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String rawPlayerName = inputView.readPlayerName();
        Players players = PlayerParser.parseToPlayers(rawPlayerName);
    }
}
