package domain.card;

public enum CardType {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
