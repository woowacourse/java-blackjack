package blackjack.domain.user;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users(final List<Name> userNames, Deck deck) {
        if (userNames.isEmpty()) {
            throw new IllegalArgumentException("유저는 최소 한 명 이상이여야 합니다.");
        }
        this.users = makeUsersBy(userNames, deck);
    }

    private List<User> makeUsersBy(final List<Name> userNames, final Deck deck) {
        final List<User> usersData = new ArrayList<>();
        for (Name name : userNames) {
            usersData.add(new User(name, getCardsOfTwoCardBy(deck)));
        }
        return usersData;
    }

    private Cards getCardsOfTwoCardBy(final Deck deck) {
        final List<Card> cards = new ArrayList<>();
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
        return new Cards(cards);
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
