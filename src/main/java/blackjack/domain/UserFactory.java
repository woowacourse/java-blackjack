package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {

    public static final int MAX_PLAYER = 8;

    public static Users generateUsers(List<String> userNames) {
        if (userNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는" + MAX_PLAYER + "명을 초과할 수 없습니다");
        }

        List<User> users = new ArrayList<>();
        users.add(new Dealer());
        users.addAll(userNames.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
        return new Users(users);
    }
}
