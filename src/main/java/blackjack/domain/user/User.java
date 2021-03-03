package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;

public abstract class User {

    private final UserDeck userDeck;

    public User(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    abstract boolean isAvailableDraw();

    public void draw(Card card) {
        userDeck.add(card);
    }

    public int getPoint() {
        return userDeck.score();
    }
}
