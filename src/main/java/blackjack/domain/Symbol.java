package blackjack.domain;

public enum Symbol {

    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String word;

    Symbol(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
