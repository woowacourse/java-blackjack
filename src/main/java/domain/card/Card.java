package domain.card;

import domain.Score;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, String letter) {
        this.suit = suit;
        this.denomination = Denomination.of(letter);
    }

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public String letter() {
        return denomination.letter();
    }

    public Score score() {
        return denomination.score();
    }

    public Suit suit() {
        return suit;
    }
}
