package blackjack.view.expressions;

import blackjack.domain.card.Denomination;

public enum DenominationExpressions {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String value;

    DenominationExpressions(final String value) {
        this.value = value;
    }

    public static String mapCardNumberToString(final Denomination denomination) {
        if (Denomination.ACE.equals(denomination)) {
            return ACE.value;
        }
        if (Denomination.JACK.equals(denomination)) {
            return JACK.value;
        }
        if (Denomination.QUEEN.equals(denomination)) {
            return QUEEN.value;
        }
        if (Denomination.KING.equals(denomination)) {
            return KING.value;
        }
        return Integer.toString(denomination.getValue());
    }
}
