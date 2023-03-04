package domain.card;

import domain.CardNumber;
import domain.Pattern;

public enum CloverCard implements Card {
    CLOVER_ACE(Pattern.CLOVER, CardNumber.ACE),
    CLOVER_TWO(Pattern.CLOVER, CardNumber.TWO),
    CLOVER_THREE(Pattern.CLOVER, CardNumber.THREE),
    CLOVER_FOUR(Pattern.CLOVER, CardNumber.FOUR),
    CLOVER_FIVE(Pattern.CLOVER, CardNumber.FIVE),
    CLOVER_SIX(Pattern.CLOVER, CardNumber.SIX),
    CLOVER_SEVEN(Pattern.CLOVER, CardNumber.SEVEN),
    CLOVER_EIGHT(Pattern.CLOVER, CardNumber.EIGHT),
    CLOVER_NINE(Pattern.CLOVER, CardNumber.NINE),
    CLOVER_TEN(Pattern.CLOVER, CardNumber.TEN),
    CLOVER_JACK(Pattern.CLOVER, CardNumber.JACK),
    CLOVER_QUEEN(Pattern.CLOVER, CardNumber.QUEEN),
    CLOVER_KING(Pattern.CLOVER, CardNumber.KING);

    private final Pattern pattern;
    private final CardNumber cardNumber;

    CloverCard(Pattern pattern, CardNumber cardNumber) {
        this.pattern = pattern;
        this.cardNumber = cardNumber;
    }

    @Override
    public String getSymbol() {
        return cardNumber.getNumber() + pattern.getPattern();
    }

    @Override
    public int getScore() {
        return cardNumber.getScore();
    }
}
