package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        for(String name : names) {
            players.add(new Player(name));
        }
    }

    public void recieveCard(Deck deck) {
        for(Player player:players) {
            player.recieveCard(deck.draw());
        }
    }
}
