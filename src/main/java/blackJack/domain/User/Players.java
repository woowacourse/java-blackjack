package blackJack.domain.User;

import java.util.ArrayList;
import java.util.List;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames, List<Integer> bettingMoneys) {
        for(int i = 0; i < playersNames.size(); i++){
            players.add(new Player(playersNames.get(i), bettingMoneys.get(i)));
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
