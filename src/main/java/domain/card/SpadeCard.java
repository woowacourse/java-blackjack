package domain.card;

public enum SpadeCard implements Card {
    ACE(Pattern.SPADE, Number.ACE),
    TWO(Pattern.SPADE, Number.TWO),
    THREE(Pattern.SPADE, Number.THREE),
    FOUR(Pattern.SPADE, Number.FOUR),
    FIVE(Pattern.SPADE, Number.FIVE),
    SIX(Pattern.SPADE, Number.SIX),
    SEVEN(Pattern.SPADE, Number.SEVEN),
    EIGHT(Pattern.SPADE, Number.EIGHT),
    NINE(Pattern.SPADE, Number.NINE),
    TEN(Pattern.SPADE, Number.TEN),
    JACK(Pattern.SPADE, Number.JACK),
    QUEEN(Pattern.SPADE, Number.QUEEN),
    KING(Pattern.SPADE, Number.KING);

    private final Pattern pattern;
    private final Number number;

    SpadeCard(Pattern pattern, Number number) {
        this.pattern = pattern;
        this.number = number;
    }

    @Override
    public String getSymbol() {
        return number.getNumber() + pattern.getPattern();
    }

    @Override
    public boolean isAce() {
        return this.number == Number.ACE;
    }

    @Override
    public Score getScore() {
        return new Score(number.getScore());
    }
}
