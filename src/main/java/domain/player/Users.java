package domain.player;

import domain.card.Deck;
import java.util.List;
import java.util.Map;

public class Users {
    private static final int MAX_SIZE = 5;

    private final List<User> users;

    private Users(List<User> users) {
        validateSize(users);
        validateUniqueNames(users);
        this.users = users;
    }

    public static Users from(Map<String, Integer> betByName) {
        return new Users(betByName.entrySet().stream()
                .map(entry -> User.of(entry.getKey(), entry.getValue()))
                .toList());
    }

    private void validateSize(List<User> users) {
        if (users.isEmpty() || users.size() > MAX_SIZE) {
            throw new IllegalArgumentException(users.size() + ": 유저 수는 1인 이상 " + MAX_SIZE + "인 이하여야 합니다.");
        }
    }

    private void validateUniqueNames(List<User> users) {
        if (users.stream()
                .map(User::getName)
                .distinct()
                .count() != users.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void drawInitialCards(Deck deck) {
        for (User user : users) {
            user.drawInitialCards(deck);
        }
    }

    public int size() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public void openInitialCards() {
        for (User user : users) {
            user.openInitialCards();
        }
    }
}
