package controller;

import domain.participant.Player;
import domain.participant.Players;
import service.GameManager;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public BlackJackController(InputView inputView, OutputView outputView, GameManager gameManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManager = gameManager;
    }

    public void playGame() {
        Players players = new Players(readUntilValidPlayers());

        gameManager.initHands();
        outputView.showInitialHands(gameManager.getDealerHand(), gameManager.getPlayersHand());

        playBlackJack(players);
        outputView.showHandResults(gameManager.getDealerHand(), gameManager.getPlayersHand());
        outputView.showGameResult(gameManager.calculateResults());
    }

    private void playBlackJack(Players players) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(player);
        }

        playDealerTurn();
    }

    private void playPlayerTurn(Player player) {
        while (!player.isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) break;

            gameManager.dealCardTo(player);
            outputView.showPlayerHand(player.getName(), gameManager.getPlayersHand());
        }
    }

    private void playDealerTurn() {
        while (gameManager.isDealerShouldHit()) {
            outputView.showDealerHitMessage();
            gameManager.dealCardToDealer();
            outputView.showDealerHand(gameManager.getDealerHand());
        }

        outputView.showDealerStandMessage();
    }

    private List<Player> readUntilValidPlayers() {
        try {
            return gameManager.createPlayers(inputView.readPlayers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readUntilValidPlayers();
        }
    }
}
