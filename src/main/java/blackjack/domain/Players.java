package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void recieveCard(Deck deck) {
        for (Player player : players) {
            player.recieveCard(deck.draw());
        }
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
