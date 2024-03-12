package view.notations;

import domain.cards.cardinfo.CardNumber;

import java.util.Arrays;

public enum CardNumberNotation {

    ACE(CardNumber.ACE, "A"),
    JACK(CardNumber.JACK, "J"),
    QUEEN(CardNumber.QUEEN, "Q"),
    KING(CardNumber.KING, "K");

    private CardNumber cardNumber;
    private String notation;

    CardNumberNotation(CardNumber cardNumber, String notation) {
        this.cardNumber = cardNumber;
        this.notation = notation;
    }

    public static String findNotationByCardNumber(CardNumber cardNumber) {
        return Arrays.stream(values()).filter(cardNumberNotation -> cardNumberNotation.cardNumber.equals(cardNumber))
                .findFirst()
                .map(cardNumberNotation -> cardNumberNotation.notation)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 카드 숫자가 없습니다."));
    }
}
