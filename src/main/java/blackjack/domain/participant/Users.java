package blackjack.domain.participant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {

    private final List<User> users;

    public Users(String[] users) {
        this.users = Arrays.stream(users)
                .map(User::new)
                .collect(Collectors.toList());
    }

    public List<String> getUserNames() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
