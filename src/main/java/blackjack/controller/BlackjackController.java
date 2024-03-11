package blackjack.controller;

import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.DealerMoreCardDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
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
        saveAllPlayersBetAmount(blackjackGame);

        outputView.printStartCards(blackjackGame.start());

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        return ExceptionHandler.retryWithSupplier(() ->
                new BlackjackGame(inputView.readPlayerNames()), outputView::printError);
    }

    private void saveAllPlayersBetAmount(final BlackjackGame blackjackGame) {
        final List<String> playersName = blackjackGame.getPlayersName();

        for (final String name : playersName) {
            saveBetAmount(blackjackGame, name);
        }
    }

    private void saveBetAmount(final BlackjackGame blackjackGame, final String name) {
        ExceptionHandler.retryWithRunnable(() ->
                blackjackGame.saveBetAmountByName(inputView.readBetAmount(name), name), outputView::printError);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<String> participantName = blackjackGame.getPlayersName();

        for (final String name : participantName) {
            runPlayerTurn(blackjackGame, name);
        }

        final DealerMoreCardDto dealerMoreCard = blackjackGame.giveDealerMoreCard();
        outputView.printDealerMoreCard(dealerMoreCard);
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
        return ExceptionHandler.retryWithSupplier(() -> inputView.readNeedMoreCard(name), outputView::printError);
    }

    private void showPlayerCards(final BlackjackGame blackjackGame, final String name) {
        final PlayerCardsDto playersCards = blackjackGame.getCardsOf(name);
        outputView.printPlayerCard(playersCards);
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalHandsScoreDto finalHandsScore = blackjackGame.getFinalHandsScore();
        final BetRevenueResultDto betRevenueResult = blackjackGame.getBetRevenueResults();
        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
