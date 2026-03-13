package domain.player;

import java.util.Collections;
import java.util.List;
import util.ErrorMessage;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = Collections.unmodifiableList(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::name)
                .toList();
    }

    public Player findByName(String name) {
        return players.stream()
                .filter(player -> player.hasSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.EXIST_PLAYER.getMessage()));
    }

    public boolean areAllBust() {
        return players.stream()
                .allMatch(Player::isBust);
    }
}