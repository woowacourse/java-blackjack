package blackjack.domain.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.Arrays;

public class CardDto {
    private final String shape;
    private final String value;

    public CardDto(Card card) {
        this.shape = translate(card.getShape());
        this.value = DtoValue.getDefaultValueOf(card.getCardNumber());
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

    public String getShape() {
        return shape;
    }

    public String getValue() {
        return value;
    }

    private enum DtoValue {
        ACE(CardNumber.ACE, "A"),
        Two(CardNumber.TWO, "2"),
        THREE(CardNumber.THREE, "3"),
        FOUR(CardNumber.FOUR, "4"),
        FIVE(CardNumber.FIVE, "5"),
        SIX(CardNumber.SIX, "6"),
        SEVEN(CardNumber.SEVEN, "7"),
        EIGHT(CardNumber.EIGHT, "8"),
        NINE(CardNumber.NINE, "9"),
        TEN(CardNumber.TEN, "10"),
        JACK(CardNumber.JACK, "J"),
        QUEEN(CardNumber.JACK, "Q"),
        KING(CardNumber.KING, "k");

        private CardNumber cardNumber;
        private String dtoValue;

        DtoValue(CardNumber cardNumber, String value) {
            this.cardNumber = cardNumber;
            this.dtoValue = value;
        }

        public static String getDefaultValueOf(CardNumber cardNumber) {
            final DtoValue targetValue = Arrays.stream(values()).filter((num) -> num.cardNumber.equals(cardNumber))
                    .findAny()
                    .orElseThrow(() -> {
                        throw new IllegalArgumentException("해당 Number는 처리되지 않습니다.");
                    });
            return targetValue.dtoValue;
        }
    }
}
