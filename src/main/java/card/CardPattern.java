package card;

public enum CardPattern {

    SPADE_PATTERN("스페이드", 0),
    CLOVER_PATTERN("클로버", 1),
    DIA_PATTERN("다이아몬드", 2),
    HEART_PATTERN("하트", 3);

    final String name;
    final int patternId;

    CardPattern(String name, int patternId) {
        this.name = name;
        this.patternId = patternId;
    }
}
