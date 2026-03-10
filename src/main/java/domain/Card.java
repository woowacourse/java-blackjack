package domain;

public enum Card {

    SPADE_ACE("A", "스페이드", 1),
    SPADE_TWO("2", "스페이드", 2),
    SPADE_THREE("3", "스페이드", 3),
    SPADE_FOUR("4", "스페이드", 4),
    SPADE_FIVE("5", "스페이드", 5),
    SPADE_SIX("6", "스페이드", 6),
    SPADE_SEVEN("7", "스페이드", 7),
    SPADE_EIGHT("8", "스페이드", 8),
    SPADE_NINE("9", "스페이드", 9),
    SPADE_TEN("10", "스페이드", 10),
    SPADE_JACK("J", "스페이드", 10),
    SPADE_QUEEN("Q", "스페이드", 10),
    SPADE_KING("K", "스페이드", 10),

    HEART_ACE("A", "하트", 1),
    HEART_TWO("2", "하트", 2),
    HEART_THREE("3", "하트", 3),
    HEART_FOUR("4", "하트", 4),
    HEART_FIVE("5", "하트", 5),
    HEART_SIX("6", "하트", 6),
    HEART_SEVEN("7", "하트", 7),
    HEART_EIGHT("8", "하트", 8),
    HEART_NINE("9", "하트", 9),
    HEART_TEN("10", "하트", 10),
    HEART_JACK("J", "하트", 10),
    HEART_QUEEN("Q", "하트", 10),
    HEART_KING("K", "하트", 10),

    DIAMOND_ACE("A", "다이아몬드", 1),
    DIAMOND_TWO("2", "다이아몬드", 2),
    DIAMOND_THREE("3", "다이아몬드", 3),
    DIAMOND_FOUR("4", "다이아몬드", 4),
    DIAMOND_FIVE("5", "다이아몬드", 5),
    DIAMOND_SIX("6", "다이아몬드", 6),
    DIAMOND_SEVEN("7", "다이아몬드", 7),
    DIAMOND_EIGHT("8", "다이아몬드", 8),
    DIAMOND_NINE("9", "다이아몬드", 9),
    DIAMOND_TEN("10", "다이아몬드", 10),
    DIAMOND_JACK("J", "다이아몬드", 10),
    DIAMOND_QUEEN("Q", "다이아몬드", 10),
    DIAMOND_KING("K", "다이아몬드", 10),

    CLUB_ACE("A", "클로버", 1),
    CLUB_TWO("2", "클로버", 2),
    CLUB_THREE("3", "클로버", 3),
    CLUB_FOUR("4", "클로버", 4),
    CLUB_FIVE("5", "클로버", 5),
    CLUB_SIX("6", "클로버", 6),
    CLUB_SEVEN("7", "클로버", 7),
    CLUB_EIGHT("8", "클로버", 8),
    CLUB_NINE("9", "클로버", 9),
    CLUB_TEN("10", "클로버", 10),
    CLUB_JACK("J", "클로버", 10),
    CLUB_QUEEN("Q", "클로버", 10),
    CLUB_KING("K", "클로버", 10);

    private final String rank;
    private final String suit;
    private final int value;

    Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return name().endsWith("_ACE");
    }

    public String getDisplayName() {
        return rank + suit;
    }
}
