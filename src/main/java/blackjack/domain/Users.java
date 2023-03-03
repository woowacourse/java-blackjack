package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.GamePoint;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users;

    public Users(final List<User> users) {
        if (users.isEmpty()) {
            throw new IllegalArgumentException("유저는 최소 한 명 이상이여야 합니다.");
        }
        this.users = users;
    }

    public List<User> getUsersGreaterThan(GamePoint point) {
        return getUserOf(user -> user.isGreaterThan(point));
    }

    public List<User> getUsersEqualTo(final GamePoint point) {
        return getUserOf(user -> user.isEqualTo(point));
    }

    public List<User> getUsersLowerThan(final GamePoint point) {
        return getUserOf(user -> user.isLowerThan(point));
    }

    private List<User> getUserOf(Predicate<User> method) {
        return users.stream()
                .filter(user -> method.test(user))
                .collect(Collectors.toList());
    }

    public List<Card> getCardsOf(final Name user) {
        final User targetUser = finUserByName(user);
        return targetUser.openCards();
    }

    public void giveCardByName(final Name userName, final Card card) {
        final User findUser = finUserByName(userName);
        findUser.draw(card);
    }

    private User finUserByName(final Name userName) {
        return users.stream()
                .filter(user -> user.isNameOf(userName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public boolean checkBustBy(final Name name) {
        return finUserByName(name).isBusted();
    }
}
