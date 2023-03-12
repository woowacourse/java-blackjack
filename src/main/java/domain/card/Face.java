package domain.card;

public enum Face {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로바"),
    DIAMOND("다이아몬드");

    private final String name;

    Face(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
