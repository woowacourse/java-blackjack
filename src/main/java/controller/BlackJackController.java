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
        readPlayersUntilValid();
        readPlayerBetAmountUntilValid();
        gameManager.dealInitialCardsToParticipants();
        outputView.showInitialHandsOfParticipants(gameManager.toDealerDto(), gameManager.toPlayersDto());

        playBlackJack();
        outputView.showHandResultsOfParticipants(gameManager.toDealerDto(), gameManager.toPlayersDto());
//        outputView.showMatchResult(gameManager.getGameResults());
        outputView.showProfitResult(gameManager.getProfitResults());
    }

    private void playBlackJack() {
        gameManager.forEachPlayer(this::playPlayerTurn);
        playDealerTurn();
    }

    private void playPlayerTurn(Player player) {
        while (!player.isBust()) {
            if (!inputView.readPlayerToHitUntilValid(player.getName())) break;

            gameManager.dealCardTo(player);
            outputView.showPlayerHand(player.getName(), gameManager.toPlayersDto());
        }
    }

    private void playDealerTurn() {
        while (gameManager.isDealerShouldHit()) {
            outputView.showDealerHitMessage();
            gameManager.dealCardToDealer();
            outputView.showDealerHand(gameManager.toDealerDto());
        }

        outputView.showDealerStandMessage();
    }

    private void readPlayersUntilValid() {
        while (true) {
            try {
                gameManager.covertAndCreatePlayers(inputView.readPlayers());
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void readPlayerBetAmountUntilValid() {
        while (true) {
            try {
                gameManager.forEachPlayerPlaceBet(inputView::readPlayerBetAmount);
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
