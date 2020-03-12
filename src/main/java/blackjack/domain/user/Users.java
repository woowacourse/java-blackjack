package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Player> getPlayer() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }
}
