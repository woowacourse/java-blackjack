package blackjack.view.display;

import blackjack.domain.card.CardNumber;

import java.util.EnumMap;
import java.util.Map;

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
    
    private static final Map<CardNumber, String> CARD_NUMBERS = new EnumMap<>(CardNumber.class);
    
    static {
        for (CardNumberDisplay cardNumber : CardNumberDisplay.values()) {
            CARD_NUMBERS.put(cardNumber.cardNumber, cardNumber.cardNumberDisplay);
        }
    }
    
    private final CardNumber cardNumber;
    private final String cardNumberDisplay;
    
    CardNumberDisplay(final CardNumber cardNumber, final String cardNumberDisplay) {
        this.cardNumber = cardNumber;
        this.cardNumberDisplay = cardNumberDisplay;
    }
    
    public static String parseCardNumber(CardNumber card) {
        return CARD_NUMBERS.get(card);
    }
}
