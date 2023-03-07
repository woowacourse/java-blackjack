package view.mapper;

import domain.card.CardNumber;

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

    private static final String NO_SUCH_CARD_NUMBER_MESSAGE = "[ERROR] 카드 숫자가 정의되어 있지 않습니다.";

    private final CardNumber cardNumber;
    private final String message;

    CardNumberMapper(final CardNumber cardNumber, final String message) {
        this.cardNumber = cardNumber;
        this.message = message;
    }

    public static String ofCardNumber(final CardNumber cardNumber) {
        return Arrays.stream(CardNumberMapper.values())
                .filter(it -> it.cardNumber == cardNumber)
                .map(cardNumberMapper -> cardNumberMapper.message)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_CARD_NUMBER_MESSAGE));
    }
}
