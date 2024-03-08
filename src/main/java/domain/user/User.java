package domain.user;

import domain.card.Card;
import domain.deck.UserDeck;

public abstract class User {
    final UserDeck userDeck;
    final Name name;

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

    public abstract boolean isPlayer();

    public Name getName() {
        return name;
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }
}
