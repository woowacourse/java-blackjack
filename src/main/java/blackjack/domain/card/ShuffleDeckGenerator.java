package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShuffleDeckGenerator implements CardDeckGenerator{

    @Override
    public CardDeck createCardDeck() {
        List<Card> cards = new LinkedList<>();
        for (final Denomination cardPattern : Denomination.values()) {
            addCard(cards, cardPattern);
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private void addCard(List<Card> cards, Denomination cardPattern) {
        for (final Suit cardNumber : Suit.values()) {
            cards.add(new Card(cardPattern, cardNumber));
        }
    }
}
