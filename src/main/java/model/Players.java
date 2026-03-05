package model;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if(players.stream().distinct().toList().size() != players.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
