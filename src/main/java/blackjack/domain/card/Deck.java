package blackjack.domain.card;

import blackjack.domain.card.exception.DeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private final List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> deck = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                deck.add(Card.of(symbol, type));
            }
        }

        return new Deck(deck);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new DeckException("뽑을 카드가 없습니다.");
        }
        return deck.remove(deck.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(deck);
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