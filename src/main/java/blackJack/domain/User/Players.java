package blackJack.domain.User;

import java.util.ArrayList;
import java.util.List;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames) {
        for (String name : playersNames) {
            this.players.add(new Player(name));
        }
    }

    public void recieveCard() {
        for (Player player : players) {
            player.initCard(CARD_CACHE.poll());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
