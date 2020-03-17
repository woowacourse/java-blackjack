package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserFactory {
    public static final int MIN_PLAYER = 2;
    public static final int MAX_PLAYER = 8;
    public static final String INVALID_AMOUNT_OF_PLAYERS = "플레이어의 수는 " + MIN_PLAYER + "명 이상, " + MAX_PLAYER + "명 이하여야 합니다";

    public static Users generateUsers(List<String> userNames) {
        Objects.requireNonNull(userNames, "유저가 null입니다");
        validatePlayerNumber(userNames);
        List<User> users = new ArrayList<>();
        users.add(new Dealer());
        users.addAll(userNames.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
        return new Users(users);
    }

    private static void validatePlayerNumber(List<String> userNames) {
        if (userNames.size() > MAX_PLAYER || userNames.size() < MIN_PLAYER) {
            throw new IllegalArgumentException(INVALID_AMOUNT_OF_PLAYERS);
        }
    }
}
