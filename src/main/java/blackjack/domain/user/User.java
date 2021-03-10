package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import java.util.List;

public abstract class User {

    private final UserDeck userDeck;

    public User(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    public void draw(Card card) {
        userDeck.add(card);
    }

    public int getScore() {
        return userDeck.deckScore();
    }

    public boolean isBust() {
        return this.getScore() > UserDeck.BLACK_JACK_NUMBER;
    }

    public boolean isBlackjack() {
        return this.getScore() == UserDeck.BLACK_JACK_NUMBER;
    }

    abstract boolean isAvailableDraw();

    public List<Card> getCards() {
        return userDeck.getUserCards();
    }


    public int compare(User user) {
        return this.getScore() - user.getScore();
    }
}
