package domain.user;

import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    protected List<Card> cards = new ArrayList<>();

    public User() {
    }

    public void receiveFirstCards(Deck deck) {
        cards.addAll(deck.dealFirstCards());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

}
