package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Deck implements Drawable {

    private static final int TOP_CARD_INDEX = 0;
    private final List<Card> deck;

    public Deck() {
        deck = Card.createDeck();
        Collections.shuffle(deck);
    }

    @Override
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("덱이 비어 있습니다.");
        }
        return deck.remove(TOP_CARD_INDEX);
    }
}
