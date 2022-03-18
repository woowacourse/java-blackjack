package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShuffleDeckGenerator implements CardDeckGenerator{

    @Override
    public CardDeck createCardDeck() {
        List<Card> cards = new LinkedList<>();
        for (final Suit suit : Suit.values()) {
            addCard(cards, suit);
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private void addCard(List<Card> cards, Suit cardPattern) {
        for (final Denomination denomination : Denomination.values()) {
            cards.add(new Card(cardPattern, denomination));
        }
    }
}
