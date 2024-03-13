package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.Map;
import java.util.Set;

public class BettingResult {

    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final Map<Player, BetAmount> bet;
    private final Dealer dealer;

    public BettingResult(Map<Player, BetAmount> bet, Dealer dealer) {
        this.bet = bet;
        this.dealer = dealer;
    }

    public double getPayout(Player player) {
        double payout = bet.get(player).calculateProfit(dealer.calculatePlayerWinStatus(player));
        if (player.isBlackJack() && dealer.isNotBlackJack()) {
            return payout * BLACKJACK_PROFIT_RATE;
        }
        return payout;
    }

    public double getDealerPayout() {
        double total = 0;
        for (Player player : bet.keySet()) {
            total += getPayout(player);
        }
        return total * -1;
    }

    public Set<Player> getPlayers() {
        return bet.keySet();
    }
}
