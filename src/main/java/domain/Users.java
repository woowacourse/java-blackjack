package domain;

import domain.card.Card;
import domain.user.Name;
import domain.user.User;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Users {

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

    public void giveEachUser(final Deck deck, final int count) {
        for (User user : users) {
            user.give(deck, count);
        }
    }

    public List<Card> getCardsOf(final User user) {
        return finUserByName(user).openCards();
    }

    public void findUserAndGive(final User user, final Card card) {
        final User findUser = finUserByName(user);
        findUser.draw(card);
    }

    private User finUserByName(final User user) {
        return users.stream()
                .filter(u -> u.hasSameName(user))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public boolean isBusted(final User user) {
        return finUserByName(user).isBusted();
    }
}
