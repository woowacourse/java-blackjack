package blackjack.model.card;

public enum Suit {

    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String displayName;

    Suit(String displayName) {
        if (displayName == null) {
            throw new IllegalArgumentException("displayName이 null입니다.");
        }

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
