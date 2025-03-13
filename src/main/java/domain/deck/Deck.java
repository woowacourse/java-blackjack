package domain.deck;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createShuffledDeck(final ShuffleStrategy shuffleStrategy) {
        final List<Card> generatedCards = generate();
        final List<Card> shuffledCards = shuffleStrategy.shuffle(generatedCards);
        return new Deck(shuffledCards);
    }

    public static Deck from(final List<Card> cards) {
        return new Deck(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 카드가 존재하지 않습니다. 지나친 도박은 삼가해주세요.");
        }
        return cards.poll();
    }

    private static List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, shape)))
                .toList();
    }
}
