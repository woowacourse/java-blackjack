package domain.card;

public enum CardType {
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String name;

    CardType(final String name) {
        this.name = name;
    }


}
