package view;

import domain.Suit;
import util.ErrorMessage;

public enum SuitDisplayName {
    SPADE(Suit.SPADE, "스페이드"),
    DIAMOND(Suit.DIAMOND,"다이아몬드"),
    HEART(Suit.HEART,"하트"),
    CLOVER(Suit.CLOVER,"클로버"),
    ;
    private final Suit suit;
    private final String message;

    SuitDisplayName(Suit suit, String message) {
        this.suit = suit;
        this.message = message;
    }

    public static String convertToMessage(Suit suit) {
        for (SuitDisplayName value : values()) {
            if(value.suit.equals(suit)){
                return value.message;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.DISPLAY.getMessage());
    }
}
