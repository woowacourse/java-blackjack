package blackjack.domain.card;

public enum CardShape {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String view;

    CardShape(final String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
