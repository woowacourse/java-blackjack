package blackjack.domain.dto;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;

public class CardDto {
    private final String shape;
    private final String value;

    public CardDto(Card card){
        this.shape = translate(card.getShape());
        this.value = translate(card.getCardNumberValue());
    }

    private String translate(final Shape shape) {
        if (shape == Shape.HEART) {
            return "하트";
        }
        if (shape == Shape.DIAMOND) {
            return "다이아몬드";
        }
        if (shape == Shape.CLOVER) {
            return "클로버";
        }
        if (shape == Shape.SPADE) {
            return "스페이드";
        }
        throw new AssertionError();
    }

    private String translate(final int cardNumberValue) {
        if (cardNumberValue == 1) {
            return "A";
        }
        if (cardNumberValue == 11) {
            return "J";
        }
        if (cardNumberValue == 12) {
            return "Q";
        }
        if (cardNumberValue == 13) {
            return "K";
        }
        return String.valueOf(cardNumberValue);
    }

    public String getShape() {
        return shape;
    }

    public String getValue() {
        return value;
    }
}
