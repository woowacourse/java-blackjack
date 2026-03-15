package domain.card;

public enum CardEmblem {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String emblem;

    CardEmblem(String emblem) {
        this.emblem = emblem;
    }

    public String displayName() {
        return emblem;
    }

}
