package participant;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void betAllPlayers(Function<String, Integer> playerBetting) {
        for (Player player : players) {
            int bettingMoney = playerBetting.apply(player.getNickname());
            player.bet(bettingMoney);
        }
    }
}
