package model;

import java.util.*;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public class Players implements Iterable<Player> {
    private final List<Player> players = new ArrayList<>();

    public Players(PlayerNames names, Deck deck) {
        for (String name : names) {
            players.add(new Player(name, deck.draw(INITIAL_DRAW_COUNT)));
        }
    }

    public Players(List<Player> players){
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
