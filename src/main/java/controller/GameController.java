package controller;

import domain.player.Player;
import dto.ParticipantResult;
import service.GameService;
import view.InputView;
import view.ResultView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        startGame();

        Map<String, Integer> userBetInfo = bettingRound();

        printInitialCards();

        hitRound();

        printFinalStatus();

        ResultView.printResult(gameService.result());
    }

    private Map<String, Integer> bettingRound() {
        List<Player> players = gameService.getPlayers();
        Map<String, Integer> userBetCost = new HashMap<>();
        for (Player player : players) {
            String playerName = player.getName();
            int bettingCost = InputView.getBettingCost(playerName);

            userBetCost.put(playerName, bettingCost);
        }

        return userBetCost;
    }

    private void printFinalStatus() {
        ResultView.printBlankLine();
        ParticipantResult dealerResult = gameService.getDealerResult();
        ResultView.printCardSumResult(dealerResult);

        List<ParticipantResult> playersEndStatus = gameService.getPlayersStatus();
        ResultView.printCardSumResult(playersEndStatus);
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
        ParticipantResult dealerStatus = gameService.getDealerResult();
        List<ParticipantResult> playersStatus = gameService.getPlayersStatus();
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

            if (player.isBust() || player.isBlackJack()) {
                break;
            }

            ResultView.printPlayerCards(player.getName(), player.getCards());
        }
    }
}
