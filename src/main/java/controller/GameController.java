package controller;

import domain.Players;
import domain.participant.Player;
import service.GameService;
import util.HitOption;
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
        outputView.printStartGame(gameService.startGame());

        processGame(gameService);

        outputView.printScore(gameService.getTotalScore());
        outputView.printResults(gameService.calculateResults());
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

    private void processGame(GameService gameService) {
        for (Player player : gameService.getPlayers()) {
            playerTurn(player, gameService);
        }
        dealerTurn(gameService);
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
