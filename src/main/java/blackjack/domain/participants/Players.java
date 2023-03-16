package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_PLAYERS_COUNT_FORMAT;

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
            throw new IllegalArgumentException(
                    String.format(INVALID_PLAYERS_COUNT_FORMAT, COUNT_MINIMUM, COUNT_MAXIMUM));
        }
    }

    public List<Player> findHitAblePlayers() {
        return players.stream()
                .filter(Player::isHitAble)
                .collect(Collectors.toList());
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }
}
