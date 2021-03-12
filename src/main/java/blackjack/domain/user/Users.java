package blackjack.domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private static final int MIN_USER_SIZE = 2;
    private static final int MAX_USER_SIZE = 8;
    private static final String USER_SIZE_EXCEPTION_MESSAGE = "인원수는 딜러포함 %d명 이상 %d이여하야 합니다. 현재 인원수: %d";
    private static final String USER_DUPLICATE_EXCEPTION_MESSAGE = "이름을 중복될 수 없습니다.";

    private final List<AbstractUser> users;

    public Users(List<AbstractUser> users) {
        List<AbstractUser> copy = new ArrayList<>(users);
        validation(copy);
        this.users = copy;
    }

    private void validation(List<AbstractUser> users) {
        int userSize = users.size();
        if (userSize < MIN_USER_SIZE || userSize > MAX_USER_SIZE) {
            throw new IllegalArgumentException(String.format(USER_SIZE_EXCEPTION_MESSAGE, MIN_USER_SIZE, MAX_USER_SIZE, userSize));
        }
        if (isDuplicate(users)) {
            throw new IllegalArgumentException(USER_DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    private boolean isDuplicate(List<AbstractUser> users) {
        return !users.stream()
                .allMatch(new HashSet<>()::add);
    }

    public AbstractUser getDealer() {
        return users.stream()
                .filter(this::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다."));
    }

    public List<AbstractUser> getPlayers() {
        return users.stream()
                .filter(this::isPlayer)
                .collect(Collectors.toList());
    }

    private boolean isDealer(AbstractUser abstractUser) {
        return abstractUser instanceof Dealer;
    }

    private boolean isPlayer(AbstractUser abstractUser) {
        return abstractUser instanceof Player;
    }
}
