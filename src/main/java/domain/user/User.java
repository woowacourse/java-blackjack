package domain.user;

import domain.card.Card;

public class User {
    final UserDeck userDeck;

    public User() {
        this.userDeck = new UserDeck();
    }

    public void addCard(Card card) {
        userDeck.pushCard(card);
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }

    public int sumUserDeck() {
        return userDeck.sumCard();
    }
}
