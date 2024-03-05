package domain;

import java.util.List;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateSize(players);
        this.players = players;
    }

    private void validateSize(List<Player> players){
        if(players.isEmpty() || players.size() > MAX_SIZE){
            throw new IllegalArgumentException("플레이어의 수는 최소 %d명 최대 %d명입니다 : 현재 %d명"
                    .formatted(MIN_SIZE, MAX_SIZE, players.size()));
        }
    }
}
