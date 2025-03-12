package model.deck;

public enum CardRank {
    ACE("A", 1, 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private static final int NO_VALUE = -1;

    private final String name;
    private final int defaultValue;
    private final int maxValue;

    CardRank(String cardName, int defaultValue) {
        this.name = cardName;
        this.defaultValue = defaultValue;
        this.maxValue = NO_VALUE;
    }

    CardRank(String name, int defaultValue, int maxValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getMaxValue() {
        if (maxValue == NO_VALUE) {
            throw new IllegalStateException("최대 값을 선택할 수 없는 카드입니다.");
        }
        return maxValue;
    }

}
