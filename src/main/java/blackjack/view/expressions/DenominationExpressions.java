package blackjack.view.expressions;

import blackjack.domain.card.Denomination;
import java.util.Arrays;

public enum DenominationExpressions {
    ACE(Denomination.ACE, "A"),
    JACK(Denomination.JACK, "J"),
    QUEEN(Denomination.QUEEN, "Q"),
    KING(Denomination.KING, "K"),
    ;

    private final Denomination denomination;
    private final String value;

    DenominationExpressions(Denomination denomination, String value) {
        this.denomination = denomination;
        this.value = value;
    }

    public static String mapCardNumberToString(final Denomination denomination) {
        return Arrays.stream(values())
                .filter(denominationExpression -> denominationExpression.denomination.equals(denomination))
                .findFirst()
                .map(denominationExpression -> denominationExpression.value)
                .orElse(Integer.toString(denomination.getValue()));
    }
}
