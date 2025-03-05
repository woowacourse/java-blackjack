package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Deck {
    private final Queue<Card> deck;

    public Deck(final Queue<Card> deck) {
        validateDuplicateCard(deck);
        this.deck = new ArrayDeque<>(deck);
    }

    public static Deck createShuffledDeck() {
        final List<Card> cards = new ArrayList<>();
        for (final CardNumber cardNumber : CardNumber.values()) {
            for (final Emblem emblem : Emblem.values()) {
                cards.add(new Card(cardNumber, emblem));
            }
        }
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    private void validateDuplicateCard(final Queue<Card> deck) {
        if (deck.stream().distinct().count() != deck.size()) {
            throw new IllegalArgumentException("덱에는 중복된 카드가 들어올 수 없습니다!");
        }
    }

    public Card pickCard() {
        return deck.poll();
    }
}
