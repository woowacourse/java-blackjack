package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validate(playerNames);
        this.players = from(playerNames);
    }

    private List<Player> from(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return players;
    }

    private void validate(List<String> players) {
        if (players.size() > 5)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }
}
