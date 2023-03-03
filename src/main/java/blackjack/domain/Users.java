package blackjack.domain;

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

    public void giveEachUser(final Deck deck, final int count) {
        for (User user : users) {
            letUserDrawFromDeck(deck, user, count);
        }
    }

    private void letUserDrawFromDeck(final Deck deck, final User user, final int count) {
        for (int i = 0; i < count; i++) {
            user.draw(deck.drawCard());
        }
    }

    public List<Card> getCardsOf(final Name user) {
        final User targetUser = finUserByName(user);
        return targetUser.openCards();
    }

    private int getUserIndexOf(final User user) {
        int targetUserIndex = users.indexOf(user);
        if (targetUserIndex == -1) {
            throw new IllegalArgumentException("없는 유저 입니다.");
        }
        return targetUserIndex;
    }

    public void findUserAndGive(final Name userName, final Card card) {
        final User findUser = finUserByName(userName);
        findUser.draw(card);
    }

    private User finUserByName(final Name userName) {
        return users.stream()
                .filter(user -> user.isNameOf(userName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }
}
