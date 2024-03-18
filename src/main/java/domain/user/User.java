package domain.user;

import domain.card.Card;
import domain.deck.UserDeck;

public class User {
    protected final UserDeck userDeck = new UserDeck();
    protected final Name name;

    public User(Name name) {
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

    public boolean isBlackjack() {
        return userDeck.isBlackjack();
    }

    public String getName() {
        return name.value();
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }
}
