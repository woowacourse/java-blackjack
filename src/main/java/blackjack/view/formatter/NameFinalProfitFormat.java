package blackjack.view.formatter;

import blackjack.dto.NameProfit;

public class NameFinalProfitFormat {
    public static String format(final NameProfit nameProfit) {
        return nameProfit.name().getRawName() + ": " + nameProfit.profit().getValue();
    }
}
