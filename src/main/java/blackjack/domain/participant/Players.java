package blackjack.domain.participant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public Map<Player, Integer> judgeResult(Dealer dealer) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        for (Player player : players) {
            result.put(player, player.calculatePrize(dealer));
        }
        return result;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
