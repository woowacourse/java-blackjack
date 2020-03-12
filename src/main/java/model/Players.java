package model;

import java.util.*;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names, Deck deck) {
        for (String name: names) {
            players.add(new Player(name, deck.draw(2)));
        }
    }

    public String getNames(){
        List<String> names = new ArrayList<>();
        players.stream().forEach(p->names.add(p.toString()));
        return String.join(",", names);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
