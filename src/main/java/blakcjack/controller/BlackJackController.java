package blakcjack.controller;

import blakcjack.domain.blackjackgame.BlackjackGame;
import blakcjack.domain.blackjackgame.GameExitException;
import blakcjack.domain.card.Deck;
import blakcjack.domain.card.EmptyDeckException;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.dto.EarningSummaryDto;
import blakcjack.dto.OutcomeSummaryDto;
import blakcjack.dto.ParticipantDto;
import blakcjack.dto.ParticipantsDto;
import blakcjack.dto.PlayerCreationDto;
import blakcjack.exception.GameTerminationException;
import blakcjack.view.InputView;

import java.util.List;
import java.util.Map;

import static blakcjack.view.InputView.takePlayerCreationInformation;
import static blakcjack.view.OutputView.printDealerAdditionalCardMessage;
import static blakcjack.view.OutputView.printFinalEarningSummary;
import static blakcjack.view.OutputView.printFinalHandsSummary;
import static blakcjack.view.OutputView.printFinalOutcomeSummary;
import static blakcjack.view.OutputView.printGameClosing;
import static blakcjack.view.OutputView.printInitialHands;
import static blakcjack.view.OutputView.printPlayerHand;

public class BlackJackController {
    public void run() {
        final BlackjackGame blackjackGame = initializeGame();
        final List<Participant> players = blackjackGame.getPlayers();
        final Participant dealer = blackjackGame.getDealer();

        drawInitialCards(blackjackGame);
        printInitialHands(ParticipantsDto.of(dealer, players));

        drawCardsInTurn(blackjackGame, players, dealer);

        notifyFinalSummary(blackjackGame, players, dealer);
    }

    private BlackjackGame initializeGame() {
        try {
            final PlayerCreationDto creationInfo = takePlayerCreationInformation();
            return new BlackjackGame(new Deck(new RandomShuffleStrategy()),
                    creationInfo.getNames(), creationInfo.getBettingMoneys());
        } catch (GameExitException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawInitialCards(final BlackjackGame blackjackGame) {
        try {
            blackjackGame.initializeHands();
        } catch (final EmptyDeckException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawCardsInTurn(final BlackjackGame blackjackGame,
                                 final List<Participant> players, final Participant dealer) {
        try {
            drawForMaximumCapability(blackjackGame, players);
            drawForMaximumCapability(blackjackGame, (Dealer) dealer);
        } catch (final EmptyDeckException e) {
            printGameClosing(e.getMessage());
            throw new GameTerminationException();
        }
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final List<Participant> players) {
        for (final Participant player : players) {
            drawForMaximumCapability(blackjackGame, player);
        }
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Participant player) {
        while (player.canDrawMoreCard() && isHitSelected(player)) {
            blackjackGame.distributeOneCard(player);
            printPlayerHand(ParticipantDto.from(player));
        }
    }

    private boolean isHitSelected(final Participant player) {
        return InputView.takeHitOrStand(player.getNameValue());
    }

    private void drawForMaximumCapability(final BlackjackGame blackjackGame, final Dealer dealer) {
        while (dealer.needsAdditionalCard()) {
            blackjackGame.distributeOneCard(dealer);
            printDealerAdditionalCardMessage();
        }
    }

    private void notifyFinalSummary(final BlackjackGame blackjackGame, final List<Participant> players, final Participant dealer) {
        printFinalHandsSummary(ParticipantsDto.of(dealer, players));

        final Map<String, Outcome> playersOutcome = blackjackGame.judgePlayersOutcome();
        final Map<Outcome, Integer> dealerOutcome = blackjackGame.judgeDealerOutcome(playersOutcome);
        printFinalOutcomeSummary(OutcomeSummaryDto.of(dealerOutcome, playersOutcome), dealer.getNameValue());
        printFinalEarningSummary(EarningSummaryDto.of(dealer, players));
    }
}
