package domain;

public enum Shape {

    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    SPADE("스페이드");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
