package domain.user;

import domain.card.Card;
import domain.deck.UserDeck;

public class User {
    protected final UserDeck userDeck;
    protected final Name name;

    public User(UserDeck userDeck, Name name) {
        this.userDeck = userDeck;
        this.name = name;
    }

    public void addCard(Card card) {
        userDeck.addCard(card);
    }

    public int sumUserDeck() {
        return userDeck.sumCard();
    }

    public boolean isBust() {
        return userDeck.isBust();
    }

    public String getName() {
        return name.value();
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }
}
