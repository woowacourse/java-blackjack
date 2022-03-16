package blackjack;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Drawable;

public class MockDeck implements Drawable {

    private final List<Card> cards;

    public MockDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public Card draw() {
        return cards.remove(0);
    }
}
