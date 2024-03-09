package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static final int SHUFFLED_DECK_SIZE = 52;

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        validateUniqueCard(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validateUniqueCard(List<Card> cards) {
        int distinctCount = (int) cards.stream()
                .distinct()
                .count();

        if (distinctCount != cards.size()) {
            throw new IllegalArgumentException("중복되는 카드가 있습니다.");
        }
    }

    public static Deck createShuffledDeck() {
        List<Card> cards = new ArrayList<>(SHUFFLED_DECK_SIZE);

        for (Shape shape : Shape.values()) {
            cards.addAll(createAllCardsOf(shape));
        }
        Collections.shuffle(cards);

        return new Deck(cards);
    }

    private static List<Card> createAllCardsOf(Shape shape) {
        return Arrays.stream(Value.values())
                .map(value -> new Card(shape, value))
                .toList();
    }

    public Card draw() {
        return cards.poll();
    }
}
