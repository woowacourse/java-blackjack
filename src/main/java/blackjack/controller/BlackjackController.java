package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.PlayerNames;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerBetAmountDto;
import blackjack.exception.NeedRetryException;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public final class BlackjackController {

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
        final PlayerNames playerNames = createPlayerNames();
        final List<PlayerBetAmountDto> playerBetAmounts = inputView.readBetAmounts(playerNames);

        return BlackjackGame.from(playerBetAmounts);
    }

    private PlayerNames createPlayerNames() {
        try {
            final List<PlayerName> playerNames = inputView.readPlayerNames();
            return new PlayerNames(playerNames);
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return createPlayerNames();
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        outputView.printStartCards(blackjackGame.getStartCards());

        blackjackGame.playGame(this::needMoreCard, this::printPlayerCard, this::printDealerMoreCard);
    }

    private boolean needMoreCard(final PlayerName name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void printPlayerCard(final PlayerName name, final Hands hands) {
        outputView.printPlayerCard(name, hands);
    }

    private void printDealerMoreCard(final int count) {
        outputView.printDealerMoreCard(count);
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        final FinalHandsScoreDto finalHandsScore = blackjackGame.getFinalHandsScore();
        final BetRevenueResultDto betRevenueResult = blackjackGame.getBetRevenueResults();
        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
