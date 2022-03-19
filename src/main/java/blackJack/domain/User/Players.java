package blackJack.domain.User;

import blackJack.domain.Card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playersNames, Map<String, Integer> bettingMoneys) {
        for (String playersName : playersNames) {
            players.add(new Player(playersName, bettingMoneys.get(playersName)));
        }
    }

    public void dealCardToPlayers(Deck deck) {
        for (Player player : players) {
            player.dealCard(deck.getCard());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
