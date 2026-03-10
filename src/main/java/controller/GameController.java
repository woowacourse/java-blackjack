package controller;

import domain.player.Player;
import dto.DealerResult;
import dto.PlayerResult;
import service.GameService;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;

public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        startGame();
        printInitialCards();

        hitRound();

        printFinalStatus();

        ResultView.printResult(gameService.result());
    }

    private void printFinalStatus() {
        DealerResult dealerResult = gameService.getDealerResult();
        ResultView.printCardSumResult(dealerResult);

        List<PlayerResult> playersEndStatus = gameService.getPlayersStatus();
        Map<String, Integer> playersTotalScore = gameService.getPlayersTotalScore();
        ResultView.printCardSumResult(playersEndStatus, playersTotalScore);
    }

    private void hitRound() {
        for (Player player : gameService.getPlayers()) {
            askPlayerHit(player);
        }

        if (gameService.dealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void printInitialCards() {
        DealerResult dealerStatus = gameService.getDealerResult();
        List<PlayerResult> playersStatus = gameService.getPlayersStatus();
        ResultView.printStartPlayersCards(dealerStatus, playersStatus);
    }

    private void startGame() {
        List<String> names = InputView.askName();
        gameService.joinPlayers(names);
        gameService.initAllPlayerCard();
    }

    private void askPlayerHit(Player player) {
        playerChooseHit(player);
        ResultView.printPlayerCards(player.getName(), player.getCards());
    }

    private void playerChooseHit(Player player) {
        while (InputView.askHit(player.getName())) {
            gameService.hit(player);

            if (player.isBust()) {
                break;
            }

            ResultView.printPlayerCards(player.getName(), player.getCards());
        }
    }
}
