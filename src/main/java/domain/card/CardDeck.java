package domain.card;

import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public int getCardsSize() {
        return cards.size();
    }
}
