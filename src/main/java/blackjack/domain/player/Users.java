package blackjack.domain.player;

import java.util.Collections;
import java.util.List;

public class Users {

    private final List<User> users;

    public Users(final List<User> users) {
        this.users = users;
    }

    public boolean hasBlackJack() {
        boolean checkUsers = users.stream()
                .anyMatch(Player::isBlackJack);
        return checkUsers;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
