package domain;

public enum CardType {
    JACK("잭"),
    KING("킹"),
    QUEEN("퀸"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String name;

    CardType(final String name) {
        this.name = name;
    }


}
