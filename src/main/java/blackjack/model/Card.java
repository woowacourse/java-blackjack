package blackjack.model;

import java.util.Random;

public class Card {
    private final Suit suit;
    private final Denomination denomination;
    private final Random random;

    public Card() {
        this.suit = generateSuit();
        this.denomination = generateDenomination();
        this.random = new Random();
    }

    protected Suit generateSuit() {
        int bound = Suit.getSize();
        int randomIndex = random.nextInt(bound);
        return Suit.findByIndex(randomIndex);
    }

    protected Denomination generateDenomination() {
        int bound = Denomination.getSize();
        int randomIndex = random.nextInt(bound);
        return Denomination.findByIndex(randomIndex);
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
