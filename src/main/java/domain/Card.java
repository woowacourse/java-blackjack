package domain;

public class Card {

    private final Denomination denomination;
    private final Suits suit;

    public Card(Denomination denomination, Suits suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
