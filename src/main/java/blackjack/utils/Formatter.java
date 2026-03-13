package blackjack.utils;

import java.text.DecimalFormat;

public final class Formatter {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private Formatter() {

    }

    public static String amountFormat(final int amount) {
        return DECIMAL_FORMAT.format(amount);
    }
}
