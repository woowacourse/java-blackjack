package domain.card;

import domain.CardNumber;
import domain.Pattern;

public enum SpadeCard implements Card {
    SPADE_ACE(Pattern.SPADE, CardNumber.ACE),
    SPADE_TWO(Pattern.SPADE, CardNumber.TWO),
    SPADE_THREE(Pattern.SPADE, CardNumber.THREE),
    SPADE_FOUR(Pattern.SPADE, CardNumber.FOUR),
    SPADE_FIVE(Pattern.SPADE, CardNumber.FIVE),
    SPADE_SIX(Pattern.SPADE, CardNumber.SIX),
    SPADE_SEVEN(Pattern.SPADE, CardNumber.SEVEN),
    SPADE_EIGHT(Pattern.SPADE, CardNumber.EIGHT),
    SPADE_NINE(Pattern.SPADE, CardNumber.NINE),
    SPADE_TEN(Pattern.SPADE, CardNumber.TEN),
    SPADE_JACK(Pattern.SPADE, CardNumber.JACK),
    SPADE_QUEEN(Pattern.SPADE, CardNumber.QUEEN),
    SPADE_KING(Pattern.SPADE, CardNumber.KING);

    private final Pattern pattern;
    private final CardNumber cardNumber;

    SpadeCard(Pattern pattern, CardNumber cardNumber) {
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
