package domain.card;

public enum Symbol {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트");

    private final String letter;

    Symbol(String letter) {
        this.letter = letter;
    }
}
