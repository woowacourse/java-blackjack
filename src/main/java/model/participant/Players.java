package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.deck.Deck;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players createByNames(List<String> names, Deck deck) {
        return new Players(names.stream()
                .map(name -> new Player(name, deck))
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
