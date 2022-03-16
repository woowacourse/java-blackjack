package blackjack.domain.bet;

import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersBet {

    private final Map<Player, Money> playerBetTable;

    public PlayersBet() {
        playerBetTable = new LinkedHashMap<>();
    }

    public void add(Player player, Money money) {
        playerBetTable.put(player, money);
    }

    public Money get(Player player) {
        return playerBetTable.get(player);
    }
}
