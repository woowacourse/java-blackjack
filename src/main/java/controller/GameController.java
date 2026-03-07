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

        // 플레이어들에 대해 히트 여부 묻기
        while(gameService.isRemainPlayer()) {
            askPlayerHit();
        }

        // 딜러 문구 출력
        if (gameService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }

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
