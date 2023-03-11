package domain.card;

import domain.Number;
import domain.Pattern;
import domain.user.Score;

public enum CloverCard implements Card {
    CLOVER_ACE(Pattern.CLOVER, Number.ACE),
    CLOVER_TWO(Pattern.CLOVER, Number.TWO),
    CLOVER_THREE(Pattern.CLOVER, Number.THREE),
    CLOVER_FOUR(Pattern.CLOVER, Number.FOUR),
    CLOVER_FIVE(Pattern.CLOVER, Number.FIVE),
    CLOVER_SIX(Pattern.CLOVER, Number.SIX),
    CLOVER_SEVEN(Pattern.CLOVER, Number.SEVEN),
    CLOVER_EIGHT(Pattern.CLOVER, Number.EIGHT),
    CLOVER_NINE(Pattern.CLOVER, Number.NINE),
    CLOVER_TEN(Pattern.CLOVER, Number.TEN),
    CLOVER_JACK(Pattern.CLOVER, Number.JACK),
    CLOVER_QUEEN(Pattern.CLOVER, Number.QUEEN),
    CLOVER_KING(Pattern.CLOVER, Number.KING);

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
