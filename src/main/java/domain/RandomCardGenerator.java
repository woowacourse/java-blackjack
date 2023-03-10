package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class RandomCardGenerator implements CardGenerator {

    private static final Random RANDOM = new Random();
    private static final Queue<Card> CARDS = new PriorityQueue<>((card1, card2) -> {
        if (RANDOM.nextBoolean()) {
            return 1;
        }
        return -1;
    });

    static {
        Arrays.stream(Denomination.values())
            .forEach(
                letter -> Arrays.stream(Suit.values())
                    .map(suit -> new Card(suit, letter))
                    .forEach(CARDS::add));
    }

    @Override
    public Card generate() {
        return CARDS.poll();
    }

}
