public enum CardValue {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    FACE(10),
    ACE(1, 11);

    private final int[] values;

    CardValue(int... values) {
        this.values = values;
    }

}
