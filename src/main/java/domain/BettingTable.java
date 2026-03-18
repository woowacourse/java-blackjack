package domain;

import domain.participant.Dealer;
import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.vo.Money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingTable {
    private static final double BLACKJACK_BONUS = 1.5;
    private final Map<Player, Money> bettingTable = new HashMap<>();

    public void placeBet(Player player, Money money) {
        bettingTable.put(player, money);
    }

    public Money calculateProfit(Player player, Dealer dealer) {
        WinStatus playerWinStatus = player.decideWinStatus(dealer);

        if (playerWinStatus == WinStatus.WIN && player.isBlackjack()) {
            return new Money(bettingTable.get(player).multiplyDouble(BLACKJACK_BONUS));
        }

        if (playerWinStatus == WinStatus.LOSS) {
            return bettingTable.get(player).negate();
        }

        if (playerWinStatus == WinStatus.DRAW) {
            return new Money(BigDecimal.ZERO);
        }

        return bettingTable.get(player);
    }

    public Money getDealerProfit(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> calculateProfit(player, dealer))
                .reduce(new Money(BigDecimal.ZERO), Money::add)
                .negate();
    }
}
