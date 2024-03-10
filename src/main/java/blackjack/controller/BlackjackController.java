package blackjack.controller;

import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.StartCardsDto;
import blackjack.dto.WinningResultDto;
import blackjack.exception.ExceptionHandler;
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

        final StartCardsDto startCardsDTO = blackjackGame.start();
        outputView.printStartCards(startCardsDTO);

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        return ExceptionHandler.retry(() -> new BlackjackGame(inputView.readPlayerNames()), outputView::printError);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> participantName = blackjackGame.getPlayersName();

        for (final String name : participantName) {
            runPlayerTurn(blackjackGame, name);
        }

        final int count = blackjackGame.giveDealerMoreCard();
        outputView.printDealerMoreCard(count, blackjackGame.getDealerName());
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
        return ExceptionHandler.retry(() -> inputView.readNeedMoreCard(name), outputView::printError);
    }

    private void showPlayerCards(final BlackjackGame blackjackGame, final String name) {
        final PlayerCardsDto playersCards = blackjackGame.getCardsOf(name);
        outputView.printPlayerCard(playersCards);
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalHandsScoreDto finalHandsScoreDTO = blackjackGame.getFinalHandsScore();
        final WinningResultDto winningResults = blackjackGame.getWinningResults();
        outputView.printFinalResult(finalHandsScoreDTO, winningResults);
    }
}
