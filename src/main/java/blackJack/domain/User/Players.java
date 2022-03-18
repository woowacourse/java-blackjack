package blackJack.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames, Map<String, Integer> bettingMoneys) {
        for (String playersName : playersNames) {
            players.add(new Player(playersName, bettingMoneys.get(playersName)));
        }
    }

    public void dealCardToPlayers() {
        for (Player player : players) {
            player.dealCard(CARD_CACHE.poll());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
