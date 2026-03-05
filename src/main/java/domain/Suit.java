package domain;

public enum Suit {
    SPACE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    ;

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
