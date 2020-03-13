package blackjack.domain.card;

import blackjack.domain.card.exceptions.DeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards implements Deck {
    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> deck) {
        return new Cards(deck);
    }

    public static Cards of(DeckFactory deckFactory) {
        return deckFactory.create();
    }

    // TODO remove
    public static Cards create() {
        List<Card> deck = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            for (Type type : Type.values()) {
                deck.add(Card.of(symbol, type));
            }
        }

        return new Cards(deck);
    }

    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new DeckException("뽑을 카드가 없습니다.");
        }
        return cards.remove(cards.size() - 1);
    }

    // TODO remove
    public void shuffle() {
        Collections.shuffle(cards);
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