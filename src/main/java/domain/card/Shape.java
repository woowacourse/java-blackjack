package domain.card;

import java.util.List;

public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static List<Shape> giveShapes() {
        return List.of(values());
    }
}
