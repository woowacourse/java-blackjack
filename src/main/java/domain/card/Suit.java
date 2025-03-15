package domain.card;

public enum Suit {

    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    ;

    private final String title;

    Suit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
