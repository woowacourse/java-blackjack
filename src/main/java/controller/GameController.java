package controller;

import domain.participant.Dealer;
import domain.GameResult;
import domain.participant.Player;

import dto.GameScoreDTO;

import service.GameService;

import util.HitOption;
import util.InputHitOptionParser;
import util.InputNameParser;

import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 1. 게임 준비
        GameService gameService = new GameService(inputPlayers());
        outputView.printStartGame(gameService.startGame());

        // 2. 게임 진행
        processGame(gameService);

        // 3. 결과 집계
        Map<String, GameResult> playerFinalResults = getPlayerFinalResults(gameService.getPlayers(), gameService.getDealer());
        outputView.printPlayerFinalResults(playerFinalResults);
    }

    private List<String> inputPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return InputNameParser.parsePlayerNames(rawPlayerNames);
    }

    private HitOption inputHitOption(Player player) {
        String rawHitOption = inputView.readHitOption(player.getName());
        return InputHitOptionParser.parseHitOption(rawHitOption);
    }

    private void processGame(GameService gameService) {
        for (Player player : gameService.getPlayers()) {
            playerTurn(player, gameService);
        }
        dealerTurn(gameService);
        outputView.printFinalScore(GameScoreDTO.from(gameService.getPlayers(), gameService.getDealer()));
    }

    private void playerTurn(Player player, GameService gameService) {
        while (!player.isBust() && inputHitOption(player) == HitOption.YES) {
            outputView.printHandCard(gameService.playerHit(player));
        }
        if(!player.isBust()) {
            outputView.printHandCard(gameService.getCurrentHand(player));
        }
    }

    private void dealerTurn(GameService gameService) {
        while(gameService.getDealer().isReceiveCard()) {
            gameService.dealerHit();
            outputView.printDealerReceiveCard();
        }
    }

    // TODO: 결과를 만드는 부분 리팩토링 필요
    private Map<String, GameResult> getPlayerFinalResults(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerFinalResults = new HashMap<>();
        int dealerWinningCount = 0;
        int dealerLosingCount = 0;

        for (Player player : players) {
            GameResult gameResult = player.compareScore(dealer.calculateScore());
            playerFinalResults.put(player.getName(), gameResult);

            if (gameResult == GameResult.WIN) {
                dealerLosingCount += 1;
            }

            if (gameResult == GameResult.LOSE) {
                dealerWinningCount += 1;
            }
        }
        outputView.printDealerFinalCount(dealerWinningCount, dealerLosingCount);
        return playerFinalResults;
    }

}
