package domain.participant.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerGroup {
    private static final int PLAYER_NUMBER_LIMIT = 4;
    private final List<Player> players;

    public PlayerGroup(List<Player> players) {
        validatePlayerNum(players);
        validateDuplicate(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public int getPlayerGroupSize() {
        return players.size();
    }

    private void validatePlayerNum(List<Player> players) {
        if (players.size() > PLAYER_NUMBER_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수가 " + players.size() + "명으로 정원인 " + PLAYER_NUMBER_LIMIT + "명을 초과합니다.");
        }
    }

    private void validateDuplicate(List<Player> players) {
        Set<String> distinctNames = players.stream()
                .map(player -> player.getName().getValueOf())
                .collect(Collectors.toSet());

        if (distinctNames.size() != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 내에 동명이인이 있습니다.");
        }
    }
}
