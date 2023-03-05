package controller;

import java.util.List;

import blackjackgame.BlackjackGame;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;
import player.Name;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public Controller(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        setGame();
        showFirstDraw();
        hitPlayers();
        dealerHit();
        printFinalCards();
        printWinningResult();
    }

    private void setGame() {
        try {
            List<String> names = inputView.readPlayerNames();
            blackjackGame.addPlayers(names);
            blackjackGame.supplyCardsToDealer();
            blackjackGame.supplyCardsToPlayers();
            outputView.printFirstDrawMessage(names);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            setGame();
        }

    }

    private void showFirstDraw() {
        DealerFirstOpenDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<PlayerOpenDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);
    }


    private void hitPlayers() {
        for (int i = 0; i < blackjackGame.countPlayer(); i++) {
            Name userName = blackjackGame.findUserNameByIndex(i);
            hitPlayer(i, userName);
        }
    }

    private void hitPlayer(int playerIndex, Name userName) {
        try {
            hitByCommand(playerIndex, userName);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            hitPlayer(playerIndex, userName);
        }
    }

    private void hitByCommand(int playerIndex, Name userName) {
        String hitCommand = inputView.readHitCommand(userName);
        HitCommand.validateCommand(hitCommand);
        while (HitCommand.isYes(hitCommand) && !blackjackGame.isBust(playerIndex)) {
            blackjackGame.supplyAdditionalCard(playerIndex);
            PlayerOpenDto playerCard = blackjackGame.getPlayerCardsByIndex(playerIndex);
            outputView.printPlayerCard(playerCard);
            if (blackjackGame.isBust(playerIndex)) {
                break;
            }
            hitCommand = inputView.readHitCommand(userName);
        }
    }

    private void dealerHit() {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printFinalCards() {
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }

    private void printWinningResult() {
        blackjackGame.calculateWinning();
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }
}
