package blackjack.controller;

import blackjack.domain.player.PlayerName;
import blackjack.domain.player.bet.BetAmount;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.exception.ExceptionHandler;
import blackjack.domain.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initGame();
        playGame(blackjackGame);
        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        final BlackjackGame blackjackGame = ExceptionHandler.retryWithSupplier(() ->
                new BlackjackGame(inputView.readPlayerNames()), outputView::printError);

        saveAllPlayersBetAmount(blackjackGame);
        return blackjackGame;
    }

    private void saveAllPlayersBetAmount(final BlackjackGame blackjackGame) {
        final List<PlayerName> playersName = blackjackGame.getPlayersName();
        final Map<PlayerName, BetAmount> playerBetAmounts = readBetAmount(playersName);

        blackjackGame.saveBetAmount(playerBetAmounts);
    }

    private Map<PlayerName, BetAmount> readBetAmount(final List<PlayerName> names) {
        return ExceptionHandler.retryWithSupplier(() -> inputView.readBetAmounts(names), outputView::printError);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        outputView.printStartCards(blackjackGame.getStartCards());

        blackjackGame.playGame(this::needMoreCard, outputView::printPlayerCard, outputView::printDealerMoreCard);
    }

    private boolean needMoreCard(final PlayerName name) {
        return ExceptionHandler.retryWithSupplier(() -> inputView.readNeedMoreCard(name), outputView::printError);
    }


    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalHandsScoreDto finalHandsScore = blackjackGame.getFinalHandsScore();
        final BetRevenueResultDto betRevenueResult = blackjackGame.getBetRevenueResults();
        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
