package blackjack.domain;

public enum Shape {
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트");

    private final String letter;

    Shape(final String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }
}
