package domain.card;

public enum SpadeCard implements Card {
    ACE(Suit.SPADE, Denomination.ACE),
    TWO(Suit.SPADE, Denomination.TWO),
    THREE(Suit.SPADE, Denomination.THREE),
    FOUR(Suit.SPADE, Denomination.FOUR),
    FIVE(Suit.SPADE, Denomination.FIVE),
    SIX(Suit.SPADE, Denomination.SIX),
    SEVEN(Suit.SPADE, Denomination.SEVEN),
    EIGHT(Suit.SPADE, Denomination.EIGHT),
    NINE(Suit.SPADE, Denomination.NINE),
    TEN(Suit.SPADE, Denomination.TEN),
    JACK(Suit.SPADE, Denomination.JACK),
    QUEEN(Suit.SPADE, Denomination.QUEEN),
    KING(Suit.SPADE, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    SpadeCard(Suit suit, Denomination denomination) {
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
