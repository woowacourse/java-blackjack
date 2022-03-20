package blackjack.domain.card;

import java.util.LinkedList;
import java.util.List;

public class TestDeckGenerator implements CardDeckGenerator {

    @Override
    public CardDeck createCardDeck() {
        List<Card> cards = new LinkedList<>();
        for (final Suit cardPattern : Suit.values()) {
            addCard(cards, cardPattern);
        }
        return new CardDeck(cards);
    }

    private void addCard(final List<Card> cards, final Suit cardPattern) {
        for (final Denomination cardNumber : Denomination.values()) {
            cards.add(Card.of(cardPattern, cardNumber));
        }
    }
}
