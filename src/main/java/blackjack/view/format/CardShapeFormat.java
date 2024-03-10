package blackjack.view.format;

public enum CardShapeFormat {
    CLOVER("클로버"),
    DIA("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트");

    private final String format;

    CardShapeFormat(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
