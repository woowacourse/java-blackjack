package blackjack.domain.card;

import blackjack.domain.card.exception.DeckException;

import java.util.*;

public class Deck {
    private final Stack<Card> deck;

    public Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck create(ProcessStrategy processStrategy) {
        Stack<Card> cards = createInOrder();
        processStrategy.shuffle(cards);
        return new Deck(cards);
    }

    private static Stack<Card> createInOrder() {
        Stack<Card> cards = new Stack<>();

        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                cards.push(new Card(symbol, type));
            }
        }

        return cards;
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new DeckException("뽑을 카드가 없습니다.");
        }
        return deck.pop();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck1 = (Deck) o;
        return Objects.equals(deck, deck1.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}