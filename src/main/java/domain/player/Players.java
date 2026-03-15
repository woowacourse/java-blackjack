package domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;
    private int currentIndex = 0;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public boolean isAllFinished() {
        return currentIndex >= players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public void passTurn() {
        currentIndex++;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean areAllBust() {
        return players.stream()
                .allMatch(Player::isBust);
    }
}
