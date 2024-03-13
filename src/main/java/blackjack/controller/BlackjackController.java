package blackjack.controller;

import blackjack.domain.player.PlayerName;
import blackjack.domain.bet.BetAmount;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.domain.BlackjackGame;
import blackjack.exception.NeedRetryException;
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
        final BlackjackGame blackjackGame = createGame();
        saveAllPlayersBetAmount(blackjackGame);

        return blackjackGame;
    }

    private BlackjackGame createGame() {
        try {
            final List<PlayerName> playerNames = inputView.readPlayerNames();
            return new BlackjackGame(playerNames);
        } catch (NeedRetryException e) {
            outputView.printError(e.getMessage());
            return createGame();
        }
    }

    private void saveAllPlayersBetAmount(final BlackjackGame blackjackGame) {
        final List<PlayerName> playersName = blackjackGame.getPlayersName();
        final Map<PlayerName, BetAmount> playerBetAmounts = inputView.readBetAmounts(playersName);

        blackjackGame.saveBetAmount(playerBetAmounts);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        outputView.printStartCards(blackjackGame.getStartCards());

        blackjackGame.playGame(this::needMoreCard, outputView::printPlayerCard, outputView::printDealerMoreCard);
    }

    private boolean needMoreCard(final PlayerName name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (NeedRetryException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalHandsScoreDto finalHandsScore = blackjackGame.getFinalHandsScore();
        final BetRevenueResultDto betRevenueResult = blackjackGame.getBetRevenueResults();
        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
