package domain.card;

public enum HeartCard implements Card {
    ACE(Suit.HEART, Denomination.ACE),
    TWO(Suit.HEART, Denomination.TWO),
    THREE(Suit.HEART, Denomination.THREE),
    FOUR(Suit.HEART, Denomination.FOUR),
    FIVE(Suit.HEART, Denomination.FIVE),
    SIX(Suit.HEART, Denomination.SIX),
    SEVEN(Suit.HEART, Denomination.SEVEN),
    EIGHT(Suit.HEART, Denomination.EIGHT),
    NINE(Suit.HEART, Denomination.NINE),
    TEN(Suit.HEART, Denomination.TEN),
    JACK(Suit.HEART, Denomination.JACK),
    QUEEN(Suit.HEART, Denomination.QUEEN),
    KING(Suit.HEART, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    HeartCard(Suit suit, Denomination denomination) {
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
