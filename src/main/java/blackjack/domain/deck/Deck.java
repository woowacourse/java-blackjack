package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.deck.exception.DeckException;

import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck createWithShuffle() {
        return createInOrder().shuffle();
    }

    private static Deck createInOrder() {
        Stack<Card> cards = new Stack<>();

        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                cards.push(new Card(symbol, type));
            }
        }

        return new Deck(cards);
    }

    private Deck shuffle() {
        Collections.shuffle(cards);
        return this;
    }

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
        Deck deck1 = (Deck) o;
        return Objects.equals(cards, deck1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}