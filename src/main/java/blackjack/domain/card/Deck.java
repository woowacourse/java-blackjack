package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck implements Drawable {

    private final Deque<Card> deck;

    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> blackjackCards = Card.createDeck();
        Collections.shuffle(blackjackCards);
        Deque<Card> deck = new ArrayDeque<>();
        for (Card card : blackjackCards) {
            deck.push(card);
        }
        return new Deck(deck);
    }

    @Override
    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("덱이 비어 있습니다.");
        }
        return deck.pop();
    }
}
