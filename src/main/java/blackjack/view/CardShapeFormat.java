package blackjack.view;

public enum CardShapeFormat {
    CLOVER("클로버"),
    DIA("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트");

    private final String name;
    CardShapeFormat(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
