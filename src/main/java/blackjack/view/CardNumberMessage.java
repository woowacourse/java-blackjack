package blackjack.view;

import blackjack.domain.CardNumber;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CardNumberMessage {
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

    private static final Map<CardNumber, CardNumberMessage> NUMBER_MESSAGE = Arrays.stream(CardNumberMessage.values())
            .collect(Collectors.toMap(CardNumberMessage::getCardNumber, Function.identity()));

    private final CardNumber cardNumber;
    private final String message;

    CardNumberMessage(CardNumber cardNumber, String message) {
        this.cardNumber = cardNumber;
        this.message = message;
    }

    public static CardNumberMessage from(CardNumber cardNumber) {
        if (NUMBER_MESSAGE.containsKey(cardNumber)) {
            return NUMBER_MESSAGE.get(cardNumber);
        }
        throw new IllegalArgumentException("해당 숫자가 존재하지 않습니다");
    }

    public String getMessage() {
        return message;
    }

    private CardNumber getCardNumber() {
        return cardNumber;
    }
}
