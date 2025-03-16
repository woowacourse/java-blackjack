package controller;

import java.util.List;
import java.util.Map;
import model.betting.Bet;
import model.participant.Dealer;
import model.deck.Deck;
import model.participant.Players;
import model.result.GameResult;
import model.participant.Player;
import model.result.InitialDealResult;
import model.result.WinningResults;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            List<String> playerNames = InputView.readPlayerNames();
            Players players = Players.from(playerNames);
            Dealer dealer = new Dealer();
            Deck deck = Deck.of();

            receiveBets(players, dealer);

            dealInitially(players, dealer, deck);
            if (continueGame(players, dealer)) {
                hitOrStandAtPlayersTurn(players, deck);
                hitOrStandAtDealerTurn(dealer, deck);
            }

            printFinalScore(players, dealer);
            WinningResults winningResults = printWinningResult(players, dealer);

            calculateRevenue(winningResults, players, dealer);
            printRevenue(players, dealer);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void receiveBets(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            int betAmount = InputView.readBetAmount(player.getName());
            dealer.receiveBet(player.makeBet(betAmount));
        }
    }

    private void dealInitially(final Players players, final Dealer dealer, final Deck deck) {
        players.getPlayers().forEach(player ->
                dealer.splitInitialDeck(deck, player)
        );
        dealer.splitInitialDeck(deck, dealer);
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

    private WinningResults printWinningResult(final Players players, final Dealer dealer) {
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<GameResult, Integer> dealerWinningResult = winningResults.decideDealerWinning();

        OutputView.printDealerFinalResult(dealerWinningResult);
        OutputView.printPlayerFinalResult(winningResults);

        return winningResults;
    }

    private void calculateRevenue(WinningResults winningResults, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            if (winningResults.isLose(player)) {
                dealer.updateBetOwnerFrom(player);
            }
            if (winningResults.isBlackjackWin(player)) {
                dealer.updateBetAmountOf(player);
            }
        }
    }

    private void printRevenue(Players players, Dealer dealer) {
        OutputView.printDealerRevenue(dealer);
        for (Player player : players.getPlayers()) {
            Bet bet = dealer.findBetByBetter(player);
            OutputView.printPlayersRevenue(player, bet);
        }
    }
}
