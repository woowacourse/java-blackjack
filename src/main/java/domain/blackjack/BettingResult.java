package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.Map;
import java.util.Set;

public class BettingResult {

    private static final double BLACKJACK_PROFIT_RATE = 1.5;
    private static final int DEALER_MULTIPLIER = -1;

    private final Map<Player, BetAmount> bet;

    public BettingResult(Map<Player, BetAmount> bet) {
        this.bet = bet;
    }

    public double getPayout(Player player, Dealer dealer) {
        double payout = bet.get(player).calculateProfit(dealer.calculatePlayerWinStatus(player));
        if (player.isBlackJack() && dealer.isNotBlackJack()) {
            return payout * BLACKJACK_PROFIT_RATE;
        }
        return payout;
    }

    public double getDealerPayout(Dealer dealer) {
        double total = 0;
        for (Player player : bet.keySet()) {
            total += getPayout(player, dealer);
        }
        return total * DEALER_MULTIPLIER;
    }

    public Set<Player> getPlayers() {
        return bet.keySet();
    }
}
