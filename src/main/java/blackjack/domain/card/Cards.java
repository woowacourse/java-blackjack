package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;

import java.util.*;

public class Cards implements Deck {
    private final Stack<Card> cards;

    private Cards(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> cards) {
        Stack<Card> stack = new Stack<>();
        stack.addAll(cards);
        return new Cards(stack);
    }

    public static Cards ofDeckFactory(DeckFactory deckFactory) {
        return deckFactory.create();
    }

    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new DeckException("뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + cards +
                '}';
    }
}