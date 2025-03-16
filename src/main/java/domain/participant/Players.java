package domain.participant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final int MAXIMUM_PLAYERS_SIZE = 4;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(List<Player> players) {
        if (players.size() > MAXIMUM_PLAYERS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 총 4명까지만 참여 가능합니다.");
        }
    }

    public Map<Player, Integer> getTotalRankSumByPlayer() {
        return players.stream()
                .collect(Collectors.toMap(player -> player,
                        Player::getTotalRankSum,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
