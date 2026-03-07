package controller;

import service.GameService;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;

public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        List<String> names = InputView.askName();
        gameService.joinPlayers(names);
        gameService.initAllPlayerCard();

        Map<String, List<String>> playersStatus = gameService.getPlayersStatus();
        ResultView.printStartPlayersCards(playersStatus);

    }
}
