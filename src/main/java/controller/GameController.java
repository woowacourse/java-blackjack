package controller;

import domain.participant.Player;
import dto.GameScoreDTO;
import service.GameService;
import util.HitOption;
import util.InputHitOptionParser;
import util.InputNameParser;
import view.InputView;
import view.OutputView;
import java.util.List;

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
        outputView.printScore(gameService.getTotalScore());
        outputView.printResults(gameService.calculateResults());
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
        outputView.printScore(GameScoreDTO.from(gameService.getPlayers(), gameService.getDealer()));
    }

    private void playerTurn(Player player, GameService gameService) {
        while (!player.isBust() && inputHitOption(player) == HitOption.YES) {
            outputView.printHandCard(gameService.playerHit(player));
        }
        if (!player.isBust()) {
            outputView.printHandCard(gameService.getCurrentHand(player));
        }
    }

    private void dealerTurn(GameService gameService) {
        while (gameService.getDealer().isReceiveCard()) {
            gameService.dealerHit();
            outputView.printDealerReceiveCard();
        }
    }
}
