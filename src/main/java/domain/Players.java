package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, Integer> getTotalNumberSumByName() {
        return players.stream()
                .collect(Collectors.toMap(player -> player, Player::getTotalNumberSum));
    }

    public List<Player> findAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player findPlayer(String name) {
        return players.stream()
            .filter(player -> player.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어의 이름입니다."));
    }

    public Players editPlayer(Player player, Player newPlayer) {
        List<Player> newPlayers = new ArrayList<>(players);
        int index = newPlayers.indexOf(player);
        newPlayers.set(index, newPlayer);
        return new Players(newPlayers);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Players players1 = (Players) object;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }
}
