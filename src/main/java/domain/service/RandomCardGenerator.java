package domain.service;

import domain.model.Card;
import domain.type.Letter;
import domain.type.Suit;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCardGenerator implements CardGenerator {

    private static final CardGenerator cardGenerator;
    private static final Random random = new Random();
    private static final Queue<Card> cards = new PriorityQueue<>((card1, card2) -> {
        if (random.nextBoolean()) {
            return 1;
        }
        return -1;
    });

    static {
        Arrays.stream(Letter.values())
            .forEach(
                letter -> Arrays.stream(Suit.values())
                    .map(suit -> new Card(suit, letter))
                    .forEach(cards::add));
        cardGenerator = new RandomCardGenerator();
    }

    private RandomCardGenerator() {}

    @Override
    public void reset() {
        cards.clear();
        Arrays.stream(Letter.values())
            .forEach(
                letter -> Arrays.stream(Suit.values())
                    .map(suit -> new Card(suit, letter))
                    .forEach(cards::add));
    }

    @Override
    public Card generate() {
        return cards.poll();
    }

    @Override
    public List<Card> generate(final int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> generate())
            .collect(Collectors.toList());
    }

    public static CardGenerator getInstance() {
        return cardGenerator;
    }
}
