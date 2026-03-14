package domain;

import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.vo.Money;

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
            return new Money(bettingTable.get(player).getValueOf() * BLACKJACK_BONUS);
        }

        if (player.getWinStatus() == WinStatus.LOSS) {
            return new Money(0);
        }

        return bettingTable.get(player);
    }

    public Money getDealerProfit() {
        return new Money(bettingTable.values()
                .stream()
                .mapToInt(money -> money.getValueOf())
                .sum());
    }
}
