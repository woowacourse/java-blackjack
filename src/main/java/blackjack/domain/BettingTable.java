package blackjack.domain;

import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.util.Map;

public class BettingTable {

    private static final String NONEXISTENT_PLAYER_EXCEPTION = "존재하지 않는 사용자입니다.";

    private final Map<Player, Betting> bettingTable;

    public BettingTable(Map<Player, Betting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public BigDecimal calculateProfitByPlayer(final Player player, final GameResult gameResult) {
        if (!bettingTable.containsKey(player)) {
            throw new IllegalArgumentException(NONEXISTENT_PLAYER_EXCEPTION);
        }

        return bettingTable.get(player).calculateProfit(gameResult);
    }
}
