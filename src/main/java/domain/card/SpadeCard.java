package domain.card;

import domain.Number;
import domain.Pattern;
import domain.user.Score;

public enum SpadeCard implements Card {
    SPADE_ACE(Pattern.SPADE, Number.ACE),
    SPADE_TWO(Pattern.SPADE, Number.TWO),
    SPADE_THREE(Pattern.SPADE, Number.THREE),
    SPADE_FOUR(Pattern.SPADE, Number.FOUR),
    SPADE_FIVE(Pattern.SPADE, Number.FIVE),
    SPADE_SIX(Pattern.SPADE, Number.SIX),
    SPADE_SEVEN(Pattern.SPADE, Number.SEVEN),
    SPADE_EIGHT(Pattern.SPADE, Number.EIGHT),
    SPADE_NINE(Pattern.SPADE, Number.NINE),
    SPADE_TEN(Pattern.SPADE, Number.TEN),
    SPADE_JACK(Pattern.SPADE, Number.JACK),
    SPADE_QUEEN(Pattern.SPADE, Number.QUEEN),
    SPADE_KING(Pattern.SPADE, Number.KING);

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
