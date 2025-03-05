package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<String, Integer> getTotalNumberSumByName() {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, Player::getTotalNumberSum));
    }

    public Player findPlayer(String name) {
        return players.stream()
            .filter(player -> player.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어의 이름입니다."));
    }
}
