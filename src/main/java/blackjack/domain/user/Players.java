package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    public static final int MAXIMUM_PLAYER_COUNT = 7;
    public static final String OVER_MAXIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE =
        String.format("참여 인원이 너무 많습니다. %d명 이내로만 가능합니다.", MAXIMUM_PLAYER_COUNT);

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validPlayerNames(playerNames);
        this.players = Collections.unmodifiableList(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void validPlayerNames(List<String> playerNames) {
        if (playerNames.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(OVER_MAXIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
            .map(User::getName)
            .collect(Collectors.toList()));
    }
}
