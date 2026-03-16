package domain;

import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.vo.Money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BettingTable {
    private static final double BLACKJACK_BONUS = 1.5;
    private final Map<Player, Money> bettingTable = new HashMap<>();

    public void placeBet(Player player, Money money) {
        bettingTable.put(player, money);
    }

    public Money calculateProfit(Player player) {
        if (player.getWinStatus() == WinStatus.WIN && player.isBlackjack()) {
            return new Money(bettingTable.get(player).multiplyDouble(BLACKJACK_BONUS));
        }

        if (player.getWinStatus() == WinStatus.LOSS) {
            return bettingTable.get(player).negate();
        }

        if (player.getWinStatus() == WinStatus.DRAW) {
            return new Money(BigDecimal.ZERO);
        }

        return bettingTable.get(player);
    }

    public Money getDealerProfit() {
        return bettingTable.keySet().stream()
                .map(this::calculateProfit)
                .reduce(new Money(0), Money::add)
                .negate();
    }
}
