package blackjack.view;

import blackjack.domain.card.CardNumber;

public enum CardNumberStrings {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String value;

    CardNumberStrings(final String value) {
        this.value = value;
    }

    public static String mapCardNumberToString(final CardNumber cardNumber) {
        if (CardNumber.ACE.equals(cardNumber)) {
            return ACE.value;
        }
        if (CardNumber.JACK.equals(cardNumber)) {
            return JACK.value;
        }
        if (CardNumber.QUEEN.equals(cardNumber)) {
            return QUEEN.value;
        }
        if (CardNumber.KING.equals(cardNumber)) {
            return KING.value;
        }
        return Integer.toString(cardNumber.getValue());
    }
}
