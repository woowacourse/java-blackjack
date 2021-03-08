package blackjack.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Users {
    private static final String DUPLICATE_NAME_ERROR_MSG = "중복되는 이름은 존재할 수 없습니다.";
    private final List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public static Users from(List<Name> names) {
        validateUsersName(names);
        return new Users(names.stream()
                .map(User::new)
                .collect(Collectors.toList()));
    }

    private static void validateUsersName(List<Name> names) {
        Set<Name> duplicateChecker = new HashSet<>(names);
        if (duplicateChecker.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MSG);
        }
    }

    public List<User> toList() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users1 = (Users) o;
        return Objects.equals(users, users1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }
}
