package model.participant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        validate(playerNames);
        return playerNames.stream()
            .map(Player::from)
            .collect(collectingAndThen(toList(), Players::new));
    }

    private static void validate(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어 수는 1명 이상이어야 합니다.");
        }
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() < playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public List<String> names() {
        return players.stream()
            .map(Player::getName)
            .toList();
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
