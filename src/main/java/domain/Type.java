package domain;

public enum Type {
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로바");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
