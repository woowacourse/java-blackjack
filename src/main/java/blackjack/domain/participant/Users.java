package blackjack.domain.participant;

import blackjack.domain.result.UserResult;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Users {

    private final List<User> users;

    public Users(List<String> users) {
        this.users = users.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public List<String> getUserNames() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public User getUserByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<UserResult> getUsersInfoWithResult(int dealerSum) {
        return users.stream()
                .map(user -> user.getUserInfoWithResult(dealerSum))
                .collect(Collectors.toList());
    }
}
