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
        registerPlayers();

        printInitialCardResult();

        playersHit();
        dealerHit();

        printFinalCardResult();
        printRankResult();

    }

    private void printInitialCardResult() {
        Map<String, List<String>> playersStatus = gameService.getPlayersStatus();
        ResultView.printStartPlayersCards(playersStatus);
    }

    private void registerPlayers() {
        List<String> names = InputView.askName();
        gameService.joinPlayers(names);
        gameService.initAllPlayerCard();
    }

    private void playersHit() {
        while(gameService.isRemainPlayer()) {
            askPlayerHit();
        }
    }

    private void askPlayerHit() {
        // TODO: 딜러 도메인 분리 후 삭제
        if(gameService.getCurrentPlayerName().equals("딜러")) {
            gameService.nextPlayer();
            return;
        }

        while(InputView.askHit(gameService.getCurrentPlayerName()) && gameService.isCurrentPlayerBust()) {
            gameService.selectHit();
            ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        }

        ResultView.printPlayerCards(gameService.getCurrentPlayerName(), gameService.getCurrentPlayerCards());
        gameService.nextPlayer();
    }

    private void printFinalCardResult() {
        Map<String, List<String>> playersEndStatus = gameService.getPlayersStatus();
        Map<String, Integer> playersTotalScore = gameService.getPlayersTotalScore();
        ResultView.printCardSumResult(playersEndStatus, playersTotalScore);
    }

    private void dealerHit() {
        if (gameService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void printRankResult() {
        ResultView.printResult(gameService.result());
    }
}
