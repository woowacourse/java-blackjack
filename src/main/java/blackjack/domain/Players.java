package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int NEXT_GAP = 1;
    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player firstPlayer() {
        return players.get(FIRST_PLAYER_INDEX);
    }

    public boolean hasNextPlayer(Player player) {
        return !player.equals(players.get(players.size() - 1));
    }

    public Player nextPlayer(Player player) {
        if (!hasNextPlayer(player)) {
            return null;
        }
        return players.get(players.indexOf(player) + NEXT_GAP);
    }
}
