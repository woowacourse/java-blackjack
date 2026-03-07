package controller;

import service.GameService;
import view.InputView;

import java.util.List;

public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        List<String> names = InputView.askName();

    }
}
