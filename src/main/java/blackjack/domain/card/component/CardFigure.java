package blackjack.domain.card.component;

public enum CardFigure {
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드");

    private final String message;

    CardFigure(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
