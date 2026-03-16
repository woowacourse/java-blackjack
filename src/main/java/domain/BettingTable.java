package domain;

import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.vo.Money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BettingTable {
    private static final long BLACKJACK_BONUS = (long) 1.5;
    private final Map<Player, Money> bettingTable = new HashMap<>();

    public void placeBet(Player player, Money money) {
        bettingTable.put(player, money);
    }

    public Money calculateProfit(Player player) {
        if (player.getWinStatus() == WinStatus.WIN && player.isBlackjack()) {
            bettingTable.put(player, new Money(bettingTable.get(player).multiplyLong(BLACKJACK_BONUS)));
        }

        if (player.getWinStatus() == WinStatus.LOSS) {
            bettingTable.put(player, bettingTable.get(player).negate());
        }

        if (player.getWinStatus() == WinStatus.DRAW) {
            bettingTable.put(player, new Money(BigDecimal.ZERO));
        }

        return bettingTable.get(player);
    }

    public Money getDealerProfit() {
        return bettingTable.values().stream()
                .reduce(new Money(0), Money::add)
                .negate();
    }
}
