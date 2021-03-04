package blackjack.domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Users {
    private static final String DUPLICATE_NAME_ERROR_MSG = "중복된 이름은 존재할 수 없습니다.";

    private final List<User> users;

    public Users(List<User> users) {
        Set<User> duplicateChecker = new HashSet<>(users);
        if (users.size() != duplicateChecker.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MSG);
        }
        this.users = users;
    }

    public void forEach(Consumer<? super User> consumer){
        users.forEach(consumer);
    }

    public Stream<User> stream(){
        return users.stream();
    }
}
