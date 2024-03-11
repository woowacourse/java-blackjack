package blackjack.domain.card;

import java.util.List;

public class CardPicker {
    private final Deck deck;

    public CardPicker(Deck deck) {
        this.deck = deck;
    }

    public List<Card> pickCards(int count) {
        return deck.pickCards(count);
    }

    public Card pickCard() {
        return deck.pickCard();
    }
}
