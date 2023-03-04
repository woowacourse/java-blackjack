package domain.card;

import domain.CardNumber;
import domain.Pattern;

public enum DiamondCard implements Card {
    DIAMOND_ACE(Pattern.DIAMOND, CardNumber.ACE),
    DIAMOND_TWO(Pattern.DIAMOND, CardNumber.TWO),
    DIAMOND_THREE(Pattern.DIAMOND, CardNumber.THREE),
    DIAMOND_FOUR(Pattern.DIAMOND, CardNumber.FOUR),
    DIAMOND_FIVE(Pattern.DIAMOND, CardNumber.FIVE),
    DIAMOND_SIX(Pattern.DIAMOND, CardNumber.SIX),
    DIAMOND_SEVEN(Pattern.DIAMOND, CardNumber.SEVEN),
    DIAMOND_EIGHT(Pattern.DIAMOND, CardNumber.EIGHT),
    DIAMOND_NINE(Pattern.DIAMOND, CardNumber.NINE),
    DIAMOND_TEN(Pattern.DIAMOND, CardNumber.TEN),
    DIAMOND_JACK(Pattern.DIAMOND, CardNumber.JACK),
    DIAMOND_QUEEN(Pattern.DIAMOND, CardNumber.QUEEN),
    DIAMOND_KING(Pattern.DIAMOND, CardNumber.KING);

    private final Pattern pattern;
    private final CardNumber cardNumber;

    DiamondCard(Pattern pattern, CardNumber cardNumber) {
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
