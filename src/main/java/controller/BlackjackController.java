package controller;

import java.util.List;
import java.util.Map;
import model.participant.Dealer;
import model.Deck.Deck;
import model.result.GameResult;
import model.participant.Player;
import model.result.ParticipantWinningResult;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            List<String> playerNames = InputView.readPlayerNames();
            Players players = Players.from(playerNames);
            Dealer dealer = new Dealer();
            Deck deck = Deck.of();

            dealInitially(players, deck, dealer);

            hitOrStandAtPlayersTurn(players, deck);
            hitOrStandAtDealerTurn(dealer, deck);

            OutputView.printFinalScore(dealer, players);
            printWinningResult(players, dealer);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void hitOrStandAtDealerTurn(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void hitOrStandAtPlayersTurn(final Players players, final Deck deck) {
        players.getPlayers().forEach(player ->
                hitOrStandAtOnePlayerTurn(deck, player)
        );
    }

    private void hitOrStandAtOnePlayerTurn(final Deck deck, final Player player) {
        while (InputView.readHit(player)) {
            player.receiveCard(deck.pick());
            OutputView.printHitResult(player);
            if (player.isBurst()) {
                break;
            }
        }
    }

    private void dealInitially(final Players players, final Deck deck, final Dealer dealer) {
        players.dealInitialCards(deck);
        dealer.dealInitialCards(deck);
        OutputView.printInitialDealResult(dealer, players);
    }

    private void printWinningResult(final Players players, final Dealer dealer) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(participantWinningResult);
    }
}
