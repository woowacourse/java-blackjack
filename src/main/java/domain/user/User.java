package domain.user;

import domain.card.Card;

import java.util.List;

public class User {
    private final UserDeck userDeck;

    public User() {
        this.userDeck = new UserDeck();
    }

    public void addCard(Card card) {
        userDeck.pushCard(card);
    }

    public List<Card> showUserDeck() {
        return userDeck.getUserDeck();
    }
}
