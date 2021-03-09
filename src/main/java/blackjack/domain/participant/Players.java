package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayersCount(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayersAsList() {
        return new ArrayList<>(players);
    }

    private void validatePlayersCount(final List<Player> players) {
        if (players.size() < 1 || players.size() > 7) {
            throw new IllegalArgumentException("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
        }
    }
}
