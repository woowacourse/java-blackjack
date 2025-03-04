public enum CardRank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10), ACE(1, 11);

    private final int[] values;  // 점수 저장

    CardRank(int... values) {
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }
}