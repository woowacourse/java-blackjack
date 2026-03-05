package domain;

import java.util.List;

public class Players {

    private final List<String> players;

    public Players(List<String> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<String> players) {
        if (players.size() > 5)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }


}
