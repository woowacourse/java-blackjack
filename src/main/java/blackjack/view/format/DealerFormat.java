package blackjack.view.format;

import blackjack.domain.dealer.Dealer;

public enum DealerFormat {
    DEALER(Dealer.DEALER_SIGNAL, "딜러");

    private final String signal;
    private final String format;

    DealerFormat(final String signal, final String format) {
        this.signal = signal;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public String getSignal() {
        return signal;
    }

    public boolean isSignal(final String name) {
        return this.name().equals(name) || format.equals(name);
    }
}
