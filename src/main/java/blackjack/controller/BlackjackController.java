package blackjack.controller;

import blackjack.domain.BetAmountRepository;
import blackjack.domain.BlackjackGame;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.Players;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.exception.NeedRetryException;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = createPlayers();

        final BlackjackGame blackjackGame = BlackjackGame.from(players);
        final BetAmountRepository betAmountRepository = saveBetAmount(players);

        playGame(blackjackGame);
        finishGame(blackjackGame, betAmountRepository);
    }

    private Players createPlayers() {
        try {
            final PlayerNames playerNames = inputView.readPlayerNames();
            return Players.from(playerNames);
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return createPlayers();
        }
    }

    private BetAmountRepository saveBetAmount(final Players players) {
        final Map<PlayerName, BetAmount> playerBetAmounts = inputView.readBetAmounts(players.getPlayerNames());

        return new BetAmountRepository(players, playerBetAmounts);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        outputView.printStartCards(blackjackGame.giveInitCards());

        blackjackGame.playGame(this::wantToHit, this::printPlayerCard, this::printDealerMoreCard);
    }

    private boolean wantToHit(final PlayerName name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return wantToHit(name);
        }
    }

    private void printPlayerCard(final PlayerName name, final Hands hands) {
        outputView.printPlayerCard(name, hands);
    }

    private void printDealerMoreCard(final int count) {
        outputView.printDealerMoreCard(count);
    }

    private void finishGame(final BlackjackGame blackjackGame, final BetAmountRepository betAmountRepository) {
        final Map<PlayerName, Hands> playersHands = blackjackGame.getPlayerHands();
        final Hands dealerHands = blackjackGame.getDealerHands();
        final FinalHandsScoreDto finalHandsScore = FinalHandsScoreDto.of(playersHands, dealerHands);

        final Map<PlayerName, BetLeverage> playersBetLeverage = blackjackGame.getPlayersBetLeverage();
        final BetRevenueResultDto betRevenueResult = betAmountRepository.calculateBetRevenue(playersBetLeverage);

        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
