package blackjack.controller;

import blackjack.dto.CardDTO;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initGame();

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        try {
            final BlackjackGame blackjackGame = new BlackjackGame(inputView.readPlayerNames());
            final StartCardsDTO startCardsDTO = blackjackGame.start();

            outputView.printStartCards(startCardsDTO);
            return blackjackGame;
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return initGame();
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> participantName = blackjackGame.getPlayersName();

        for (final String name : participantName) {
            runPlayerTurn(blackjackGame, name);
        }

        final int count = blackjackGame.giveDealerMoreCard();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final String name) {
        boolean isFirst = true;

        while (isContinue(blackjackGame, name)) {
            blackjackGame.addCardToPlayers(name);
            showPlayerCards(blackjackGame, name);
            isFirst = false;
        }

        if (isFirst) {
            showPlayerCards(blackjackGame, name);
        }
    }

    private boolean isContinue(final BlackjackGame blackjackGame, final String name) {
        return blackjackGame.isPlayerAliveByName(name) && needMoreCard(name);
    }

    private boolean needMoreCard(final String name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void showPlayerCards(final BlackjackGame blackjackGame, final String name) {
        final List<CardDTO> cards = blackjackGame.getCardsOf(name);
        outputView.printPlayerCard(name, cards);
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalResultDTO finalResultDTO = blackjackGame.getFinalResults();
        final WinningResultDTO winningResults = blackjackGame.getWinningResults();
        outputView.printFinalResult(finalResultDTO, winningResults);
    }
}
