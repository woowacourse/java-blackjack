package domain.card;

public enum TrumpSuit {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private String name;

    TrumpSuit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
