package view;

import domain.CardNumber;
import domain.CardType;

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
    private final String value;

    CardNumberMapper(CardNumber cardNumber, String value) {
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public static String getCardNumber(CardNumber cardNumber) {
        return Arrays.stream(CardNumberMapper.values())
                .filter(it -> it.cardNumber == cardNumber)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 카드 타입이 없습니다."))
                .value;
    }
}
