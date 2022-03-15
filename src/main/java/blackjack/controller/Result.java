package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Winner;
import blackjack.view.OutputView;

public class Result {

    public void openResult(final Dealer dealer, final Players players) {
        openDealerResult(dealer);
        openPlayersResult(players);
    }

    private void openDealerResult(final Dealer dealer) {
        OutputView.printResult(dealer.getName(), dealer.getCards(), dealer.getTotal());
        OutputView.printNewLine();
    }

    private void openPlayersResult(final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printResult(player.getName(), player.getCards(), player.getTotal());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    public void win(final Dealer dealer, final Players players) {
        Winner winner = new Winner();
        for (Player player : players.getPlayers()) {
            winner.decide(dealer, player);
        }
        OutputView.printWinnerTitle();
        winDealer(winner, dealer, players);
        winPlayers(winner, players);
    }

    private void winDealer(final Winner winner, Dealer dealer, final Players players) {
        OutputView.printDealerScore(dealer.getName(), winner.countLoser(players), winner.countWinner());
    }

    private void winPlayers(final Winner winner, final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerScore(player.getName(), winner.contains(player));
        }
    }
}
