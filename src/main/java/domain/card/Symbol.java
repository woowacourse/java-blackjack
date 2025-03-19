package domain.card;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    COLVER("클로버");

    private final String name;

    Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
