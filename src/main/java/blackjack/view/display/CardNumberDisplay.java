package blackjack.view.display;

import blackjack.domain.card.CardNumber;

import java.util.Arrays;

public enum CardNumberDisplay {
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
    KING(CardNumber.KING, "K"),
    ;
    
    private final CardNumber cardNumber;
    private final String cardNumberDisplay;
    
    CardNumberDisplay(final CardNumber cardNumber, final String cardNumberDisplay) {
        this.cardNumber = cardNumber;
        this.cardNumberDisplay = cardNumberDisplay;
    }
    
    public static String parseCardNumber(CardNumber card) {
        return Arrays.stream(CardNumberDisplay.values())
                .filter(displayCard -> displayCard.cardNumber.equals(card))
                .findAny()
                .map(displayCard -> displayCard.cardNumberDisplay)
                .orElseThrow();
    }
}
