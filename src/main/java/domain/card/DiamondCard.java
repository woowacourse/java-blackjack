package domain.card;

public enum DiamondCard implements Card {
    ACE(Suit.DIAMOND, Denomination.ACE),
    TWO(Suit.DIAMOND, Denomination.TWO),
    THREE(Suit.DIAMOND, Denomination.THREE),
    FOUR(Suit.DIAMOND, Denomination.FOUR),
    FIVE(Suit.DIAMOND, Denomination.FIVE),
    SIX(Suit.DIAMOND, Denomination.SIX),
    SEVEN(Suit.DIAMOND, Denomination.SEVEN),
    EIGHT(Suit.DIAMOND, Denomination.EIGHT),
    NINE(Suit.DIAMOND, Denomination.NINE),
    TEN(Suit.DIAMOND, Denomination.TEN),
    JACK(Suit.DIAMOND, Denomination.JACK),
    QUEEN(Suit.DIAMOND, Denomination.QUEEN),
    KING(Suit.DIAMOND, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    DiamondCard(Suit suit, Denomination denomination) {
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
