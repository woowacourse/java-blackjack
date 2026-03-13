package controller;

import domain.Players;
import domain.batting.Money;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.GameStartDto;
import dto.HandScoreDto;
import service.GameService;
import util.HitOption;
import util.InputBattingParser;
import util.InputHitOptionParser;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = inputPlayers();
        GameService gameService = new GameService(players);

        playerBatting(players);

        GameStartDto gameStartDTO = gameService.startGame();
        outputView.printStartGame(gameStartDTO);

        processGame(gameService);

        outputView.printResults(gameService.calculateResults());
        outputView.printScore(gameService.getTotalScore());
    }

    private Players inputPlayers() {
        while (true) {
            try {
                String rawPlayerNames = inputView.readPlayerNames();
                return Players.fromString(rawPlayerNames);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private HitOption inputHitOption(Player player) {
        while (true) {
            try {
                String rawHitOption = inputView.readHitOption(player.getName());
                return InputHitOptionParser.parseHitOption(rawHitOption);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private Money inputBattingMoney(Player player) {
        while (true) {
            try {
                String rawBattingMoney = inputView.readBatting(player.getName());
                return InputBattingParser.parseBattingParser(rawBattingMoney);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void processGame(GameService gameService) {
        for (Player player : gameService.getPlayers()) {
            playerTurn(player, gameService);
        }
        dealerTurn(gameService);
    }

    private void playerTurn(Player player, GameService gameService) {
        // 플레이어가 턴이 끝나지 않았고(Bust나 Blackjack이 아님), Hit을 원할 때까지 반복
        while (!player.isFinished() && inputHitOption(player) == HitOption.YES) {
            gameService.playerHit(player);
            outputView.printHandCardWithScore(HandScoreDto.from(player));
        }

        // 반복문이 끝났는데 아직 안 끝난 상태(Hit 상태)라면 Stay 처리
        if (!player.isFinished()) {
            gameService.playerStay(player);
            outputView.printHandCardWithScore(HandScoreDto.from(player));
        }
    }

    private void dealerTurn(GameService gameService) {
        Dealer dealer = gameService.getDealer();

        while (!dealer.isFinished() && dealer.isReceiveCard()) {
            gameService.dealerHit();
        }
        if (!dealer.isFinished()) {
            gameService.dealerStay();
        }
    }

    private void playerBatting(Players players) {
        for (Player player : players) {
            player.battingMoney(inputBattingMoney(player));
        }
    }
}
