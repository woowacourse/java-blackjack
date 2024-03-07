package blackjack.model;

import java.util.Random;

public class RandomCardGenerator implements CardGenerator {
    private final Random random = new Random();

    @Override
    public Card pick() {
        Suit suit = generateSuit();
        Denomination denomination = generateDenomination();
        return new Card(suit, denomination);
    }

    private Suit generateSuit() {
        int bound = Suit.getSize();
        int randomIndex = random.nextInt(bound);
        return Suit.findByIndex(randomIndex);
    }

    private Denomination generateDenomination() {
        int bound = Denomination.getSize();
        int randomIndex = random.nextInt(bound);
        return Denomination.findByIndex(randomIndex);
    }
}
