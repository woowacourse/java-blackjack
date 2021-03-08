package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

public class Users<T extends AbstractUser> {
    private static final int MIN_USER_SIZE = 2;
    private static final int MAX_USER_SIZE = 8;
    private static final String USER_SIZE_EXCEPTION_MESSAGE = "인원수는 딜러포함 %d명 이상 %d이여하야 합니다. 현재 인원수: %d";

    private final List<T> users;

    public Users(List<T> users) {
        List<T> copy = new ArrayList<>(users);
        validation(copy);
        this.users = copy;
    }

    private void validation(List<T> users) {
        int userSize = users.size();
        if (userSize < MIN_USER_SIZE || userSize > MAX_USER_SIZE) {
            throw new IllegalArgumentException(String.format(USER_SIZE_EXCEPTION_MESSAGE, MIN_USER_SIZE, MAX_USER_SIZE, userSize));
        }
    }
}
