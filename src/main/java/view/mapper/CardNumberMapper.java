package view.mapper;

import domain.cards.cardinfo.CardNumber;
import java.util.Arrays;

public enum CardNumberMapper {

    ACE(CardNumber.ACE, "A"),
    TWO(CardNumber.TWO, "2"),
    THREE(CardNumber.THREE, "3"),
    FOUR(CardNumber.FOUR, "4"),
    FIVE(CardNumber.FIVE, "5"),
    SIX(CardNumber.SIX, "6"),
    SEVEN(CardNumber.SEVEN, "7"),
    EIGHT(CardNumber.EIGHT, "8"),
    NINE(CardNumber.NINE, "9"),
    TEN(CardNumber.TEN, "10"),
    JACK(CardNumber.JACK, "J"),
    QUEEN(CardNumber.QUEEN, "Q"),
    KING(CardNumber.KING, "K");

    private final CardNumber cardNumber;
    private final String expression;

    CardNumberMapper(CardNumber cardNumber, String expression) {
        this.cardNumber = cardNumber;
        this.expression = expression;
    }

    public static String toExpression(CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(cardNumberMapper -> cardNumberMapper.cardNumber == cardNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카드의 숫자입니다."))
                .expression;
    }
}
