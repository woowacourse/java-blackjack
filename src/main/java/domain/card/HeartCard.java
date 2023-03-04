package domain.card;

import domain.CardNumber;
import domain.Pattern;

public enum HeartCard implements Card {
    HEART_ACE(Pattern.HEART, CardNumber.ACE),
    HEART_TWO(Pattern.HEART, CardNumber.TWO),
    HEART_THREE(Pattern.HEART, CardNumber.THREE),
    HEART_FOUR(Pattern.HEART, CardNumber.FOUR),
    HEART_FIVE(Pattern.HEART, CardNumber.FIVE),
    HEART_SIX(Pattern.HEART, CardNumber.SIX),
    HEART_SEVEN(Pattern.HEART, CardNumber.SEVEN),
    HEART_EIGHT(Pattern.HEART, CardNumber.EIGHT),
    HEART_NINE(Pattern.HEART, CardNumber.NINE),
    HEART_TEN(Pattern.HEART, CardNumber.TEN),
    HEART_JACK(Pattern.HEART, CardNumber.JACK),
    HEART_QUEEN(Pattern.HEART, CardNumber.QUEEN),
    HEART_KING(Pattern.HEART, CardNumber.KING);

    private final Pattern pattern;
    private final CardNumber cardNumber;

    HeartCard(Pattern pattern, CardNumber cardNumber) {
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
