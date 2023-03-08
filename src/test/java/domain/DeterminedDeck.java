package domain;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class DeterminedDeck implements Deck {
    private final Stack<Card> deck;

    public DeterminedDeck(Stack<Card> cards) {
        this.deck = cards;
    }

    public static DeterminedDeck of(List<Card> cards) {
        return new DeterminedDeck(generateCardDummy(cards));
    }

    private static Stack<Card> generateCardDummy(List<Card> cards) {
        Stack<Card> cardDummy = new Stack<>();
        cards.forEach(cardDummy::push);
        return cardDummy;
    }

    @Override
    public Card draw() {
        try {
            return this.deck.pop();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
