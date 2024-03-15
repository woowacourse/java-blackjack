package blackjack.view.formatter;

import blackjack.dto.NameProfit;

public class NameProfitFormat {
    public static String format(final NameProfit nameProfit) {
        return nameProfit.name().getValue() + ": " + nameProfit.profit().getValue();
    }
}
