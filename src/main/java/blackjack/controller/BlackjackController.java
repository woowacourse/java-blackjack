package blackjack.controller;

import blackjack.domain.BetAmountRepository;
import blackjack.domain.BlackjackGame;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Hands;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.UserName;
import blackjack.domain.player.PlayerNames;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.StartCardsDto;
import blackjack.exception.NeedRetryException;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final PlayerNames playerNames = createPlayerNames();

        final BlackjackGame blackjackGame = BlackjackGame.of(playerNames, this::playerWantToHit);
        final BetAmountRepository betAmountRepository = saveBetAmount(playerNames);

        playGame(blackjackGame);
        finishGame(blackjackGame, betAmountRepository);
    }

    private PlayerNames createPlayerNames() {
        try {
            return inputView.readPlayerNames();
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return createPlayerNames();
        }
    }

    private boolean playerWantToHit(final UserName name) {
        try {
            return inputView.readNeedMoreCard(name);
        } catch (final NeedRetryException e) {
            outputView.printError(e.getMessage());
            return playerWantToHit(name);
        }
    }

    private BetAmountRepository saveBetAmount(final PlayerNames playerNames) {
        final Map<UserName, BetAmount> playerBetAmounts = new LinkedHashMap<>();

        playerNames.getNames().forEach(name -> playerBetAmounts.put(name, inputView.readBetAmount(name)));

        return new BetAmountRepository(playerBetAmounts);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        blackjackGame.giveStartCards();

        final StartCardsDto startCardsDto = StartCardsDto.of(blackjackGame.getPlayersHands(),
                blackjackGame.getDealerOpenedHands());

        outputView.printStartCards(startCardsDto);

        blackjackGame.playGame(this::printPlayerCard);
    }

    private void printPlayerCard(final UserName name, final Hands hands) {
        if (name.equals(new UserName(Dealer.DEALER_NAME))) {
            outputView.printDealerMoreCard();
            return;
        }
        outputView.printPlayerCard(name, hands);
    }

    private void finishGame(final BlackjackGame blackjackGame, final BetAmountRepository betAmountRepository) {
        final Map<UserName, Hands> playersHands = blackjackGame.getPlayersHands();
        final Hands dealerHands = blackjackGame.getDealerHands();
        final FinalHandsScoreDto finalHandsScore = FinalHandsScoreDto.of(playersHands, dealerHands);

        final Map<UserName, BetLeverage> playersBetLeverage = blackjackGame.getPlayersBetLeverage();
        final BetRevenueResultDto betRevenueResult = betAmountRepository.calculateBetRevenue(playersBetLeverage);

        outputView.printFinalResult(finalHandsScore, betRevenueResult);
    }
}
