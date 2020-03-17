package blackjack.domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users;

    public Users(List<String> userNames) {
        this(userNames.stream()
                .map(User::new)
                .toArray(User[]::new));
    }

    public Users(User[] users) {
        this.users = Arrays.asList(users);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getNames() {
        return this.users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
