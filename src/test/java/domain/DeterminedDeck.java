package domain;

import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;

public final class DeterminedDeck implements Deck {
    private final Deque<Card> deck;

    public DeterminedDeck(Deque<Card> cards) {
        this.deck = cards;
    }

    public static DeterminedDeck from(List<Card> cards) {
        return new DeterminedDeck(generateCardDummy(cards));
    }

    private static Deque<Card> generateCardDummy(List<Card> cards) {
        Deque<Card> cardDummy = new ArrayDeque<>();
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
