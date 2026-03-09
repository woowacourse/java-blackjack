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

        while(gameService.isRemainPlayer()) {
            askPlayerHit();
        }

        if (gameService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }

        Map<String, List<String>> playersEndStatus = gameService.getPlayersStatus();
        Map<String, Integer> playersTotalScore = gameService.getPlayersTotalScore();
        ResultView.printCardSumResult(playersEndStatus, playersTotalScore);

        ResultView.printResult(gameService.result());

    }

    private void askPlayerHit() {
        if(gameService.getCurrentPlayerName().equals("딜러")) {
            gameService.nextPlayer();
            return;
        }

        playerChooseHit();
        ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        gameService.nextPlayer();
    }

    public void playerChooseHit() {
        while(InputView.askHit(gameService.getCurrentPlayerName())) {
            gameService.selectHit();

            if(gameService.isCurrentPlayerBust()) {
                break;
            }

            ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        }
    }
}
