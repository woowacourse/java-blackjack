package domain;

import java.util.List;

public class Dealer {

    private static final int PLAYER_MAX_SIZE = 10;

    private final List<Player> players;


    public Dealer(List<Player> players) {
        if (players.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최대 " + PLAYER_MAX_SIZE + "명입니다.");
        }
        this.players = players;
    }
}
