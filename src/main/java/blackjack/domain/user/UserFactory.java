package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {

    public static final int MAX_PLAYER = 8;
    public static final String CANNOT_EXCEED_MAX_PLAYERS = "플레이어의 수는" + MAX_PLAYER + "명을 초과할 수 없습니다";

    public static Users generateUsers(List<String> userNames) {
        validatePlayerNumber(userNames);
        List<Player> players = userNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Users(players);
    }

    private static void validatePlayerNumber(List<String> userNames) {
        if (userNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(CANNOT_EXCEED_MAX_PLAYERS);
        }
    }
}
