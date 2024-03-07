package blackjack.view;

public enum WinningStatusFormat {
    LOSE("패"),
    DRAW("무"),
    WIN("승");

    private final String format;

    WinningStatusFormat(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
