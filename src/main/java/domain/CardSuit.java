package domain;

public enum CardSuit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String description;

    CardSuit(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
