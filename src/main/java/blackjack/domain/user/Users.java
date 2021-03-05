package blackjack.domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Users extends ArrayList<User> {
    private static final String DUPLICATE_NAME_ERROR_MSG = "중복된 이름은 존재할 수 없습니다.";

    public Users(List<User> users) {
        Set<User> duplicateChecker = new HashSet<>(users);
        if (users.size() != duplicateChecker.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MSG);
        }
        this.addAll(users);
    }
}
