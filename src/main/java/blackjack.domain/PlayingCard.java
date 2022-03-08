package blackjack.domain;

public class PlayingCard {
    private final Suit suit;
    private final Denomination denomination;

    private PlayingCard(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static PlayingCard of(Suit suit, Denomination denomination) {
        return new PlayingCard(suit, denomination);
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
