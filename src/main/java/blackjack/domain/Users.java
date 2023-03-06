package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.GamePoint;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Users {
    private static final int USER_HAVE_BIGGER_POINT = 1;
    private static final int USER_HAVE_SAME_POINT = 0;
    private static final int USER_HAVE_LOWER_POINT = -1;
    private final List<User> users;

    public Users(final List<Name> userNames, Deck deck) {
        if (userNames.isEmpty()) {
            throw new IllegalArgumentException("유저는 최소 한 명 이상이여야 합니다.");
        }
        this.users = makeUsersBy(userNames, deck);
    }

    private List<User> makeUsersBy(final List<Name> userNames, final Deck deck) {
        final ArrayList<User> usersData = new ArrayList<>();
        for (Name name : userNames) {
            usersData.add(new User(name, getCardsOfTwoCardBy(deck)));
        }
        return usersData;
    }

    private Cards getCardsOfTwoCardBy(final Deck deck) {
        final ArrayList<Card> cards = new ArrayList<>();
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
        return new Cards(cards);
    }

    public List<User> getUsersGreaterThan(GamePoint point) {
        return getUserOf(user -> user.pointCompare(point) == USER_HAVE_BIGGER_POINT);
    }

    public List<User> getUsersEqualTo(final GamePoint point) {
        return getUserOf(user -> user.pointCompare(point) == USER_HAVE_SAME_POINT);
    }

    public List<User> getUsersLowerThan(final GamePoint point) {
        return getUserOf(user -> user.pointCompare(point) == USER_HAVE_LOWER_POINT);
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

    public User finUserByName(final Name userName) {
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
