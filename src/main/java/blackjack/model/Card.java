package blackjack.model;

import java.util.Random;

public class Card {
    private final Suit suit;
    private final Denomination denomination;
    private final Random random = new Random();

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    private Suit generateSuit(final NumberGenerator numberGenerator) {
        int bound = Suit.getSize();
        int randomIndex = numberGenerator.pick() % bound;
        return Suit.findByIndex(randomIndex);
    }

    private Denomination generateDenomination(final NumberGenerator numberGenerator) {
        int bound = Denomination.getSize();
        int randomIndex = numberGenerator.pick() % bound;
        return Denomination.findByIndex(randomIndex);
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
