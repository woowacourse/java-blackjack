package domain.card;

import domain.Number;
import domain.Pattern;
import domain.user.Score;

public enum DiamondCard implements Card {
    ACE(Pattern.DIAMOND, Number.ACE),
    TWO(Pattern.DIAMOND, Number.TWO),
    THREE(Pattern.DIAMOND, Number.THREE),
    FOUR(Pattern.DIAMOND, Number.FOUR),
    FIVE(Pattern.DIAMOND, Number.FIVE),
    SIX(Pattern.DIAMOND, Number.SIX),
    SEVEN(Pattern.DIAMOND, Number.SEVEN),
    EIGHT(Pattern.DIAMOND, Number.EIGHT),
    NINE(Pattern.DIAMOND, Number.NINE),
    TEN(Pattern.DIAMOND, Number.TEN),
    JACK(Pattern.DIAMOND, Number.JACK),
    QUEEN(Pattern.DIAMOND, Number.QUEEN),
    KING(Pattern.DIAMOND, Number.KING);

    private final Pattern pattern;
    private final Number number;

    DiamondCard(Pattern pattern, Number number) {
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
