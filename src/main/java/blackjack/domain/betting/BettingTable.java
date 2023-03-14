package blackjack.domain.betting;

import blackjack.domain.participant.PlayerName;
import java.util.Map;

public class BettingTable {

    private final Map<PlayerName, Betting> bettingTable;

    public BettingTable(final Map<PlayerName, Betting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public Betting getPlayerBetting(final PlayerName playerName) {
        validate(playerName);
        return bettingTable.get(playerName);
    }

    private void validate(final PlayerName playerName) {
        if (!bettingTable.containsKey(playerName)) {
            throw new IllegalArgumentException("베팅하지 않은 플레이어입니다.");
        }
    }
}
