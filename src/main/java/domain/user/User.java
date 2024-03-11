package domain.user;

import domain.card.Card;
import domain.deck.UserDeck;
import java.util.List;

public abstract class User {
    protected final UserDeck userDeck;

    public User(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    public void addCard(Card card) {
        userDeck.addCard(card);
    }

    public int sumUserDeck() {
        return userDeck.sumCard();
    }

    public boolean busted() {
        return userDeck.busted();
    }

    public abstract boolean isPlayer();

    public List<Card> getVisibleCards() {
        return userDeck.getVisibleCards();
    }

    public List<Card> getAllCards() {
        return userDeck.getCards();
    }

    public abstract String getNameValue();
}
