package domain.card;

import domain.Number;
import domain.Pattern;
import domain.user.Score;

public enum CloverCard implements Card {
    ACE(Pattern.CLOVER, Number.ACE),
    TWO(Pattern.CLOVER, Number.TWO),
    THREE(Pattern.CLOVER, Number.THREE),
    FOUR(Pattern.CLOVER, Number.FOUR),
    FIVE(Pattern.CLOVER, Number.FIVE),
    SIX(Pattern.CLOVER, Number.SIX),
    SEVEN(Pattern.CLOVER, Number.SEVEN),
    EIGHT(Pattern.CLOVER, Number.EIGHT),
    NINE(Pattern.CLOVER, Number.NINE),
    TEN(Pattern.CLOVER, Number.TEN),
    JACK(Pattern.CLOVER, Number.JACK),
    QUEEN(Pattern.CLOVER, Number.QUEEN),
    KING(Pattern.CLOVER, Number.KING);

    private final Pattern pattern;
    private final Number number;

    CloverCard(Pattern pattern, Number number) {
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
