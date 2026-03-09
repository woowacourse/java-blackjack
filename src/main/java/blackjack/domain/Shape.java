package blackjack.domain;

public enum Shape {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Shape from(String name) {
        return java.util.Arrays.stream(values())
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Shape입니다: " + name));
    }
}
