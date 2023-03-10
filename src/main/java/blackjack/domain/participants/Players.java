package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final static int COUNT_MINIMUM = 2;
    private final static int COUNT_MAXIMUM = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayersCount(players);
        this.players = players;
    }

    private void validatePlayersCount(final List<Player> players) {
        if (players.size() < COUNT_MINIMUM || players.size() > COUNT_MAXIMUM) {
            throw new IllegalArgumentException("플레이어 인원 수는 최소 " + COUNT_MINIMUM + "명 최대 " + COUNT_MAXIMUM + "명입니다.");
        }
    }

    public List<Player> findHitAblePlayers() {
        return players.stream()
                .filter(Player::isAbleToHit)
                .collect(Collectors.toList());
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }
}
