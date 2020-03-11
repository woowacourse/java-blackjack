package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.UserCards;
import java.util.List;

public abstract class User {

    protected UserCards cards;

    public User() {
    }

    public void receiveFirstCards(Deck deck) {
        cards.put(deck.dealFirstCards());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

}
