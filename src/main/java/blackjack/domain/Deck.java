package blackjack.domain;

import java.util.Queue;

public class Deck implements Drawable {

    private final Queue<Card> deck;

    public Deck() {
        deck = Card.createDeck();
    }

    @Override
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("덱이 비어 있습니다.");
        }
        return deck.remove();
    }
}
