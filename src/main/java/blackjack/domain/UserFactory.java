package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {
    public static Users generateUsers(List<String> userNames) {
        List<User> users = new ArrayList<>();
        users.add(new Dealer());
        users.addAll(userNames.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
        return new Users(users);
    }
}
