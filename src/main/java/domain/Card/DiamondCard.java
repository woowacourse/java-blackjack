package domain.Card;

import domain.Number;
import domain.Pattern;

public enum DiamondCard implements Card{
    DIAMOND_ACE(Pattern.DIAMOND, Number.ACE),
    DIAMOND_TWO(Pattern.DIAMOND, Number.TWO),
    DIAMOND_THREE(Pattern.DIAMOND, Number.THREE),
    DIAMOND_FOUR(Pattern.DIAMOND, Number.FOUR),
    DIAMOND_FIVE(Pattern.DIAMOND, Number.FIVE),
    DIAMOND_SIX(Pattern.DIAMOND, Number.SIX),
    DIAMOND_SEVEN(Pattern.DIAMOND, Number.SEVEN),
    DIAMOND_EIGHT(Pattern.DIAMOND, Number.EIGHT),
    DIAMOND_NINE(Pattern.DIAMOND, Number.NINE),
    DIAMOND_TEN(Pattern.DIAMOND, Number.TEN),
    DIAMOND_JACK(Pattern.DIAMOND, Number.JACK),
    DIAMOND_QUEEN(Pattern.DIAMOND, Number.QUEEN),
    DIAMOND_KING(Pattern.DIAMOND, Number.KING);

    private final Pattern pattern;
    private final Number number;

    DiamondCard(Pattern pattern, Number number) {
        this.pattern = pattern;
        this.number = number;
    }
}
