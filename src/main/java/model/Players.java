package model;

import java.util.*;

public class Players implements Iterable<Player> {
    private final List<Player> players = new ArrayList<>();

    public Players(PlayerNames names, Deck deck, int initialDrawCount) {
        for (String name : names) {
            players.add(new Player(name, deck, initialDrawCount));
        }
    }

    public Players(List<Player> players) {
        this.players.addAll(players);
    }

    public String getNames() {
        List<String> names = new ArrayList<>();

        for (Player player : players) {
            names.add(player.getName());
        }
        return String.join(",", names);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
