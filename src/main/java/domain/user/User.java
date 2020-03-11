package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.List;

public abstract class User {

    protected Cards cards = new Cards();

    public void receiveFirstCards(Deck deck) {
        cards.put(deck.dealFirstCards());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

}
