package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Player;

public class Bets {
    private final Map<Player, Bet> bets;

    public Bets() {
        bets = new LinkedHashMap<>();
    }

    public void addBet(Player player, int amount) {
        bets.put(player, new Bet(amount));
    }

    public Bet getBet(Player player) {
        if (!bets.containsKey(player)) {
            throw new IllegalStateException("플레이어가 존재하지 않을 경우의 수가 없습니다.");
        }

        return bets.get(player);
    }

    public int getAmount(Player player) {
        return getBet(player).getAmount();
    }
}
