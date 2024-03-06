package domain;

public enum Shape {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIA("다이아몬드");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
