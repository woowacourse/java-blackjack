package blackjack.domain;

public enum CardShape {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String description;

    CardShape(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
