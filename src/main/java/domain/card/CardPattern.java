package domain.card;

public enum CardPattern {

    SPADE_PATTERN("스페이드"),
    CLOVER_PATTERN("클로버"),
    DIA_PATTERN("다이아몬드"),
    HEART_PATTERN("하트");

    final String name;

    CardPattern(String name) {
        this.name = name;
    }
}
