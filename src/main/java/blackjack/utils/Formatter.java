package blackjack.utils;

import java.text.DecimalFormat;

public final class Formatter {

    private Formatter() {

    }

    public static String amountFormat(final int amount) {
        final DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(amount);
    }
}
