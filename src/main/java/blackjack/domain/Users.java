package blackjack.domain;

import java.util.List;
import java.util.function.Consumer;

public class Users {

    private final List<User> users;

    public Users(List<User> users) {
        this.users = List.copyOf(users);
    }

    public List<String> getNames() {
        return users.stream()
                .map(User::getName)
                .toList();
    }

    public boolean isAllBurst() {
        return users.stream().allMatch(User::isBurst);
    }

    public void forEach(Consumer<User> action) {
        users.forEach(action);
    }
}
