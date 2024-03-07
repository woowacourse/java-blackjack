package blackjack.view.format;

public enum DealerFormat {
    DEALER("딜러");

    private final String format;

    DealerFormat(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public boolean isNot(final String input) {
        return !this.name().equals(input);
    }

    public boolean isSignal(final String name) {
        return this.name().equals(name) || format.equals(name);
    }
}
