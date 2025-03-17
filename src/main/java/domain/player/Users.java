package domain.player;

import java.util.LinkedHashMap;
import java.util.List;

public class Users {
    
    private static final int MAX_SIZE = 5;

    private final List<User> users;

    private Users(List<User> users) {
        validateSize(users);
        validateUniqueNames(users);
        this.users = users;
    }

    public static Users from(LinkedHashMap<String, Integer> betByName) {
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
                .distinct()
                .count() != users.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public int size() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }
}
