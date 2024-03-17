package model.card;

public enum CardShape {

    SPACE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    ;

    private final String name;

    CardShape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
