package blackjack.domain;

import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.util.Map;

public class BettingTable {

    private final Map<Player, Betting> bettingTable;

    public BettingTable(Map<Player, Betting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public BigDecimal calculateProfitByPlayer(final Player player, final GameResult gameResult) {
        return bettingTable.get(player).calculateProfit(gameResult);
    }
}
