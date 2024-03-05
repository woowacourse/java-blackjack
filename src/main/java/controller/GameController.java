package controller;

import domain.Game;
import domain.Player;
import view.OutputView;

public class GameController {
    private final OutputView outputView;

    public GameController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void start() {
        Player player1 = new Player();
        Player player2 = new Player();
        Game game = new Game(player1, player2);
        outputView.printAfterStartGame(game.start());
    }
}
