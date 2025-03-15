package controller;

import java.util.List;
import java.util.Map;
import model.participant.Dealer;
import model.deck.Deck;
import model.participant.Players;
import model.result.GameResult;
import model.participant.Player;
import model.result.InitialDealResult;
import model.result.ParticipantWinningResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            List<String> playerNames = InputView.readPlayerNames();
            Players players = Players.from(playerNames);
            Dealer dealer = new Dealer();
            Deck deck = Deck.of();

            dealInitially(players, dealer, deck);
            if (continueGame(players, dealer)) {
                hitOrStandAtPlayersTurn(players, deck);
                hitOrStandAtDealerTurn(dealer, deck);
            }

            printFinalScore(players, dealer);
            printWinningResult(players, dealer);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void dealInitially(final Players players, final Dealer dealer, final Deck deck) {
        dealer.dealInitialCards(deck);
        players.getPlayers().forEach(participant ->
                participant.dealInitialCards(deck)
        );
        OutputView.printInitialDeal(players, dealer);
    }

    private boolean continueGame(final Players players, final Dealer dealer) {
        InitialDealResult initialDealResult = InitialDealResult.from(dealer, players);
        return !initialDealResult.isAllPlayersLose(players);
    }

    private void hitOrStandAtPlayersTurn(final Players players, final Deck deck) {
        players.getPlayers().forEach(player ->
                hitOrStandAtOnePlayerTurn(deck, player)
        );
    }

    private void hitOrStandAtOnePlayerTurn(final Deck deck, final Player player) {
        while (player.canHit() && InputView.readHit(player)) {
            player.receiveCard(deck.pick());
            OutputView.printPlayerHitResult(player);
        }
    }

    private static void hitOrStandAtDealerTurn(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.receiveCard(deck.pick());
            OutputView.printDealerHitResult();
        }
    }
    private static void printFinalScore(Players players, Dealer dealer) {
        OutputView.printDealerFinalScore(dealer);
        OutputView.printPlayersFinalScore(players);
    }

    private void printWinningResult(final Players players, final Dealer dealer) {
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = participantWinningResult.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(participantWinningResult);
    }
}
