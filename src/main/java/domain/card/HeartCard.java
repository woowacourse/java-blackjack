package domain.card;

import domain.Number;
import domain.Pattern;
import domain.user.Score;

public enum HeartCard implements Card {
    HEART_ACE(Pattern.HEART, Number.ACE),
    HEART_TWO(Pattern.HEART, Number.TWO),
    HEART_THREE(Pattern.HEART, Number.THREE),
    HEART_FOUR(Pattern.HEART, Number.FOUR),
    HEART_FIVE(Pattern.HEART, Number.FIVE),
    HEART_SIX(Pattern.HEART, Number.SIX),
    HEART_SEVEN(Pattern.HEART, Number.SEVEN),
    HEART_EIGHT(Pattern.HEART, Number.EIGHT),
    HEART_NINE(Pattern.HEART, Number.NINE),
    HEART_TEN(Pattern.HEART, Number.TEN),
    HEART_JACK(Pattern.HEART, Number.JACK),
    HEART_QUEEN(Pattern.HEART, Number.QUEEN),
    HEART_KING(Pattern.HEART, Number.KING);

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
