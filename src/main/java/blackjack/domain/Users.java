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

    public Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .findFirst()
                .map(user -> (Dealer) user)
                .orElseThrow(() -> new NullPointerException("딜러가 없습니다"));
    }
}
