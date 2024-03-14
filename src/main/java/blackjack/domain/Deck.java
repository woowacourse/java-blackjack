package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Deck {

    private final Queue<Card> cards = new ArrayDeque<>();

    public Deck(List<Card> cards) {
        validateDuplicated(cards);

        this.cards.addAll(cards);
    }

    public static Deck createShuffledDeck() {
        return new Deck(createShuffledCards());
    }

    private static List<Card> createShuffledCards() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .map(Deck::createShapeCards)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Collections.shuffle(cards);

        return cards;
    }

    private static List<Card> createShapeCards(CardShape cardShape) {
        return Arrays.stream(CardRank.values())
                .map(cardRank -> new Card(cardRank, cardShape))
                .toList();
    }

    private void validateDuplicated(List<Card> cards) {
        if (getUniqueSize(cards) != cards.size()) {
            throw new IllegalArgumentException("중복 카드는 존재할 수 없습니다.");
        }
    }

    private long getUniqueSize(List<Card> cards) {
        return cards.stream()
                .distinct()
                .count();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 카드가 존재하지 않습니다.");
        }

        return cards.poll();
    }
}
