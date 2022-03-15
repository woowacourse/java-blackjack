package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck implements Drawable {

    private final Stack<Card> deck;

    public Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> blackjackCards = Card.createDeck();
        Collections.shuffle(blackjackCards);
        Stack<Card> deck = new Stack<>();
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
