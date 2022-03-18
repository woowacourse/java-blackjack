package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;
    private int turn;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void nextTurn() {
        this.turn++;
    }

    public boolean hasNextTurn() {
        return this.turn < players.size();
    }

    public Player turnPlayer() {
        return this.players.get(turn);
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow();
    }
}
