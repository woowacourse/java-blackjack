package domain;

import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Cards cards;

    public Game(Player... players) {
        this.players = Arrays.asList(players);
        cards = new Cards();
    }

    public void start() {
        for (Player player : players) {
            pickTwoCards(player);
        }
    }

    private void pickTwoCards(final Player player) {
        player.add(cards.pick());
        player.add(cards.pick());
    }
}
