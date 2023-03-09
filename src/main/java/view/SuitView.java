package view;

import domain.type.Suit;

public enum SuitView {

    SPADE("스페이드"), HEART("하트"), DIAMOND("다이아몬드"), CLUB("클럽");

    private final String sign;

    SuitView(final String sign) {
        this.sign = sign;
    }

    public static SuitView from(final Suit suit) {
        return SuitView.valueOf(suit.name());
    }

    public String getSign() {
        return sign;
    }
}
