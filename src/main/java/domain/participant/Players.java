package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String NOT_FOUND_PLAYER_ERROR = "[ERROR] 해당 플레이어 이름과 일치하는 플레이어가 없습니다.";

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public Player findPlayerByPlayerName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PLAYER_ERROR));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
