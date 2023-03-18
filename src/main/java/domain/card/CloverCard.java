package domain.card;

public enum CloverCard implements Card {
    ACE(Suit.CLOVER, Denomination.ACE),
    TWO(Suit.CLOVER, Denomination.TWO),
    THREE(Suit.CLOVER, Denomination.THREE),
    FOUR(Suit.CLOVER, Denomination.FOUR),
    FIVE(Suit.CLOVER, Denomination.FIVE),
    SIX(Suit.CLOVER, Denomination.SIX),
    SEVEN(Suit.CLOVER, Denomination.SEVEN),
    EIGHT(Suit.CLOVER, Denomination.EIGHT),
    NINE(Suit.CLOVER, Denomination.NINE),
    TEN(Suit.CLOVER, Denomination.TEN),
    JACK(Suit.CLOVER, Denomination.JACK),
    QUEEN(Suit.CLOVER, Denomination.QUEEN),
    KING(Suit.CLOVER, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    CloverCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    @Override
    public String getSymbol() {
        return denomination.getDenomination() + suit.getSuit();
    }

    @Override
    public Score getScore() {
        return new Score(denomination.getScore());
    }

    @Override
    public boolean isAce() {
        return this.denomination == Denomination.ACE;
    }
}
