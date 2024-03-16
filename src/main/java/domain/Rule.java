package domain;

import dto.GameResult;
import dto.PlayerResult;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    public GameResult calculateProfits(final Players players, final Dealer dealer) {
        final List<PlayerResult> playerProfits = new ArrayList<>();
        int totalProfit = 0;
        for (final Player player : players.getPlayers()) {
            final int profit = profit(player, dealer);
            totalProfit += profit;
            final PlayerResult playerProfit = new PlayerResult(player.name(), profit);
            playerProfits.add(playerProfit);
        }
        final PlayerResult dealerProfit = new PlayerResult(dealer.name(), -totalProfit);
        return new GameResult(playerProfits, dealerProfit);
    }

    public int profit(final Player player, final Dealer dealer) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return 0;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return -player.betAmount();
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return (int) (player.betAmount() * 1.5);
        }
        if (dealer.isBust() && player.isBust()) {
            return -player.betAmount();
        }
        if (dealer.isBust() && !player.isBust()) {
            return player.betAmount();
        }
        if (!dealer.isBust() && player.isBust()) {
            return -player.betAmount();
        }
        if (dealer.score() > player.score()) {
            return -player.betAmount();
        }
        if (dealer.score() < player.score()) {
            return player.betAmount();
        }
        return 0;
    }
}
