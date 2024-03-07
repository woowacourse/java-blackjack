package domain.user;

import domain.card.Card;

public class User {
    final UserDeck userDeck;
    final Name name;

    public User(Name name) {
        this.userDeck = new UserDeck();
        this.name = name;
    }

    public void addCard(Card card) {
        userDeck.pushCard(card);
    }

    public Name getName() {
        return name;
    }

    public UserDeck getUserDeck() {
        return userDeck;
    }

    public int sumUserDeck() {
        return userDeck.sumCard();
    }
}
