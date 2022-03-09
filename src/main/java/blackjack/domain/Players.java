package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final int CAPACITY = 8;
    private final List<Player> players;

    public Players(List<Player> players) {
        validateCapacity(players);
        this.players = new ArrayList<>(players);
    }

    private void validateCapacity(List<Player> players) {
        if (players.size() > CAPACITY) {
            throw new IllegalArgumentException("인원수는 8명을 넘을 수 없습니다.");
        }
    }
}
