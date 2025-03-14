package participant;

import deck.Deck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = names.stream()
                .map(nickname -> new Player(nickname, new BetMoney(1000)))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void prepareAllPlayers(Deck deck) {
        for (Player player : players) {
            player.prepareGame(deck.draw(), deck.draw());
        }
    }
}
