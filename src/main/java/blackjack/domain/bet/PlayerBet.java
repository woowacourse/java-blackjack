package blackjack.domain.bet;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerBet {

    private final Map<Player, Money> playerBetTable;

    public PlayerBet() {
        playerBetTable = new HashMap<>();
    }

    public void add(Player player, Money money) {
        playerBetTable.put(player, money);
    }

    public Money get(Player player) {
        return playerBetTable.get(player);
    }
}
