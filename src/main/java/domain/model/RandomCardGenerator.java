package domain.model;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class RandomCardGenerator implements CardGenerator {

    private static final Random random = new Random();
    private static final Queue<Card> cards = new PriorityQueue<>((card1, card2) -> {
        if (random.nextBoolean()) {
            return 1;
        }
        return -1;
    });
    ;

    static {
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

}
