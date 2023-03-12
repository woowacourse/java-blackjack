package domain.card;

public enum HeartCard implements Card {
    ACE(Pattern.HEART, Number.ACE),
    TWO(Pattern.HEART, Number.TWO),
    THREE(Pattern.HEART, Number.THREE),
    FOUR(Pattern.HEART, Number.FOUR),
    FIVE(Pattern.HEART, Number.FIVE),
    SIX(Pattern.HEART, Number.SIX),
    SEVEN(Pattern.HEART, Number.SEVEN),
    EIGHT(Pattern.HEART, Number.EIGHT),
    NINE(Pattern.HEART, Number.NINE),
    TEN(Pattern.HEART, Number.TEN),
    JACK(Pattern.HEART, Number.JACK),
    QUEEN(Pattern.HEART, Number.QUEEN),
    KING(Pattern.HEART, Number.KING);

    private final Pattern pattern;
    private final Number number;

    HeartCard(Pattern pattern, Number number) {
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
