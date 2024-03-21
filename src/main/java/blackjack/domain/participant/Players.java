package blackjack.domain.participant;

import java.util.List;

public class Players {

    private static final int MIN_PLAYER_SIZE = 1;
    private static final int MAX_PLAYER_SIZE = 4;

    private final List<Player> players;


    public Players(List<Player> players) {
        validateSize(players);
        this.players = players;
    }


    public List<Player> getPlayers() {
        return players;
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("플레이어의 수는 %d ~ %d명 이어야 한다.", MIN_PLAYER_SIZE, MAX_PLAYER_SIZE));
        }
    }
}
