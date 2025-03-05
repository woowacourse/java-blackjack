package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<TrumpCard> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public void addTrumpCard(TrumpCard card) {
        cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }
}
