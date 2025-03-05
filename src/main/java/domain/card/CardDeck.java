package domain.card;

import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card hitCard() {
        return cards.removeFirst();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
