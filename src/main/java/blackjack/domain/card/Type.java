package blackjack.domain.card;

public enum Type {
    SPADE("스페이드", "♠"),
    DIAMOND("다이아몬드", "◆"),
    HEART("하트", "♥"),
    CLUB("클로버", "♣");

    private final String name;
    private final String icon;

    Type(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
