package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = Collections.unmodifiableList(users);
    }

    public List<User> getUsers() {
        return this.users;
    }
}
