package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.participant.Dealer;
import model.Deck.Deck;
import model.participant.Participants;
import model.result.GameResult;
import model.participant.Player;
import model.result.ParticipantWinningResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            List<String> playerNames = InputView.readPlayerNames();
            Participants participants = Participants.from(playerNames);
            Deck deck = Deck.of();

            dealInitially(participants, deck);

            hitOrStandAtPlayersTurn(participants, deck);
            hitOrStandAtDealerTurn(participants, deck);

            OutputView.printFinalScores(participants);
            printWinningResult(participants);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void dealInitially(final Participants participants, final Deck deck) {
        participants.dealInitialCards(deck);
        OutputView.printInitialDeal(participants);
    }

    private void hitOrStandAtPlayersTurn(final Participants participants, final Deck deck) {
        participants.getPlayers().forEach(player ->
                hitOrStandAtOnePlayerTurn(deck, player)
        );
    }

    private void hitOrStandAtOnePlayerTurn(final Deck deck, final Player player) {
        while (player.canHit() && InputView.readHit(player)) {
            player.receiveCard(deck.pick());
            OutputView.printDealResultOf(player);
        }
    }

    private static void hitOrStandAtDealerTurn(final Participants participants, final Deck deck) {
        Dealer dealer = participants.getDealer();
        while (dealer.canHit()) {
            dealer.receiveCard(deck.pick());
            OutputView.printDealerDealResult();
        }
    }

    private void printWinningResult(final Participants participants) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(participants);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(participantWinningResult);
    }
}
