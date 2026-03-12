package blackjack.model.bet;

import blackjack.model.user.Player;
import java.util.Map;

public class BetAmounts {

    private final Map<Player, BetAmount> betAmounts;

    public BetAmounts(Map<Player, BetAmount> betAmounts) {
        this.betAmounts = betAmounts;
    }

    public int calculateWinPayout(Player player) {
        BetAmount betAmount = betAmounts.get(player);
        return betAmount.getAmount();
    }

    public int calculateLosePayout(Player player) {
        BetAmount betAmount = betAmounts.get(player);
        return -betAmount.getAmount();
    }

    public int calculateBlackjackPayout(Player player) {
        BetAmount betAmount = betAmounts.get(player);
        return (int) Math.round(betAmount.getAmount() * 1.5);
    }

    public int calculateDrawPayout() {
        return 0;
    }
}
