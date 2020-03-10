package blackjack.domain.Card;

public enum Figure {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드");

    private final String figure;

    Figure(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }
}
