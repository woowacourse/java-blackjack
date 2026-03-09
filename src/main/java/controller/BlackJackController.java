package controller;

import domain.participant.Player;
import service.GameManager;
import view.InputView;
import view.OutputView;

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
        readUntilValidPlayers();
        gameManager.dealInitialCardsToParticipants();
        outputView.showInitialHandsOfParticipants(gameManager.toDealerHandDto(), gameManager.toPlayersHandDto());

        playBlackJack();
        outputView.showHandResultsOfParticipants(gameManager.toDealerHandDto(), gameManager.toPlayersHandDto());
        outputView.showGameResult(gameManager.calculateResults());
    }

    private void playBlackJack() {
        gameManager.forEachPlayer(this::playPlayerTurn);
        playDealerTurn();
    }

    private void playPlayerTurn(Player player) {
        while (!player.isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) break;

            gameManager.dealCardTo(player);
            outputView.showPlayerHand(player.getName(), gameManager.toPlayersHandDto());
        }
    }

    private void playDealerTurn() {
        while (gameManager.isDealerShouldHit()) {
            outputView.showDealerHitMessage();
            gameManager.dealCardToDealer();
            outputView.showDealerHand(gameManager.toDealerHandDto());
        }

        outputView.showDealerStandMessage();
    }

    private void readUntilValidPlayers() {
        while (true) {
            try {
                gameManager.addPlayers(inputView.readPlayers());
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
