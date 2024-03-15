package blackjack.controller;

import blackjack.domain.BetAmountRepository;
import blackjack.domain.BlackjackGame;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Hands;
import blackjack.domain.player.Dealer;
import blackjack.domain.card.Deck;
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
        final BlackjackGame blackjackGame = initGame();
        final BetAmountRepository betAmountRepository = saveBetAmount(blackjackGame);

        playGame(blackjackGame);
        finishGame(blackjackGame, betAmountRepository);
    }

    private BlackjackGame initGame() {
        final Deck deck = Deck.create();
        final Dealer dealer = new Dealer();
        final Players players = Players.from(createPlayerNames());

        return new BlackjackGame(players, dealer, deck);
    }

    private PlayerNames createPlayerNames() {
        try {
            return inputView.readPlayerNames();
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return createPlayerNames();
        }
    }

    private BetAmountRepository saveBetAmount(final BlackjackGame blackjackGame) {
        final PlayerNames playerNames = blackjackGame.getPlayerNames();
        final Map<PlayerName, BetAmount> playerBetAmounts = inputView.readBetAmounts(playerNames);

        return new BetAmountRepository(playerBetAmounts);
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
        final FinalHandsScoreDto finalHandsScore = blackjackGame.getFinalHandsScore();

        final Map<PlayerName, BetLeverage> playersBetLeverage = blackjackGame.getPlayersBetLeverage();
        final BetRevenueResultDto betRevenueResult = betAmountRepository.calculateBetRevenue(playersBetLeverage);

        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
