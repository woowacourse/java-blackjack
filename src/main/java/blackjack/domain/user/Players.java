package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names, List<Double> moneyGroup) {
        for (int i = 0; i < names.size(); i++) {
            this.players.add(new Player(names.get(i), moneyGroup.get(i)));
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
