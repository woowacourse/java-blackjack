package blackjack.domain.participant;

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

    public User findUserByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public int getTotalProfit(int dealerSum) {
        return users.stream()
                .mapToInt(user -> user.calculateProfit(dealerSum))
                .sum();
    }
}
