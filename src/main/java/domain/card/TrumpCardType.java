package domain.card;

public enum TrumpCardType {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버"),
    ;

    private final String name;

    TrumpCardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
