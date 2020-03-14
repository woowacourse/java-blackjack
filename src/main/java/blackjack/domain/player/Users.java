package blackjack.domain.player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Users {
    private final List<User> users;

    public Users(List<String> userNames) {
        this.users = userNames.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public Stream<User> stream() {
        return this.users.stream();
    }

    public List<User> getUsers() {
        return users;
    }
}
