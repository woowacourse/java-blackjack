package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
    private static final int MIN_PLAYER = 1;
    private static final int MAX_PLAYER = 8;
    private static final String CANNOT_EXCEED_MAX_PLAYERS = "플레이어의 수는 " + MIN_PLAYER+ "명 ~ " +MAX_PLAYER + "명 사이여야 합니다";

    public static List<Player> generatePlayers(List<String> userNames) {
        validatePlayerNumber(userNames);
        return userNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void validatePlayerNumber(List<String> userNames) {
        if (userNames.size() < MIN_PLAYER || userNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(CANNOT_EXCEED_MAX_PLAYERS);
        }
    }
}
