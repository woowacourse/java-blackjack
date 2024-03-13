package view;

import static domain.constant.CardNumber.ACE;
import static domain.constant.CardNumber.EIGHT;
import static domain.constant.CardNumber.FIVE;
import static domain.constant.CardNumber.FOUR;
import static domain.constant.CardNumber.JACK;
import static domain.constant.CardNumber.KING;
import static domain.constant.CardNumber.NINE;
import static domain.constant.CardNumber.QUEEN;
import static domain.constant.CardNumber.SEVEN;
import static domain.constant.CardNumber.SIX;
import static domain.constant.CardNumber.TEN;
import static domain.constant.CardNumber.THREE;
import static domain.constant.CardNumber.TWO;

import domain.constant.CardNumber;
import java.util.Arrays;

public enum CardNumberView {
    ACE_VIEW(ACE, "A"),
    TWO_VIEW(TWO, "2"),
    THREE_VIEW(THREE, "3"),
    FOUR_VIEW(FOUR, "4"),
    FIVE_VIEW(FIVE, "5"),
    SIX_VIEW(SIX, "6"),
    SEVEN_VIEW(SEVEN, "7"),
    EIGHT_VIEW(EIGHT, "8"),
    NINE_VIEW(NINE, "9"),
    TEN_VIEW(TEN, "10"),
    JACK_VIEW(JACK, "J"),
    QUEEN_VIEW(QUEEN, "Q"),
    KING_VIEW(KING, "K");

    private final CardNumber cardNumber;
    private final String symbol;

    CardNumberView(CardNumber cardNumber, String symbol) {
        this.cardNumber = cardNumber;
        this.symbol = symbol;
    }

    public static CardNumberView getViewByNumber(CardNumber cardNumber) {
        return Arrays.stream(values())
                .filter(cardNumberView -> cardNumberView.cardNumber == cardNumber)
                .findFirst()
                .orElse(null);
    }

    public String getSymbol() {
        return symbol;
    }
}
