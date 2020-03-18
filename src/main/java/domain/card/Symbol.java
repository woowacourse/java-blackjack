package domain.card;

public enum Symbol {
    ACE(1, "A", new AceCalculator()),
    TWO(2, "2", (sum) -> 2),
    THREE(3, "3", (sum) -> 3),
    FOUR(4, "4", (sum) -> 4),
    FIVE(5, "5", (sum) -> 5),
    SIX(6, "6", (sum) -> 6),
    SEVEN(7, "7", (sum) -> 7),
    EIGHT(8, "8", (sum) -> 8),
    NINE(9, "9", (sum) -> 9),
    TEN(10, "10", (sum) -> 10),
    JACK(10, "J", (sum) -> 10),
    QUEEN(10, "Q", (sum) -> 10),
    KING(10, "K", (sum) -> 10);

    private final int value;
    private final String pattern;
    private final SymbolCalculator calculator;

    Symbol(int value, String pattern, SymbolCalculator calculator) {
        this.value = value;
        this.pattern = pattern;
        this.calculator = calculator;
    }

    public int getValue() {
        return value;
    }

    public String getPattern() {
        return pattern;
    }

    int calculate(int sum) {
        return calculator.calculate(sum);
    }

    interface SymbolCalculator {
        int calculate(int sum);
    }

    private static class AceCalculator implements SymbolCalculator {
        /*
         * JOKER_VALUE는 특정 상황에서 ACE를 크게 계산하기 때문에, 새롭게 지은 명칭입니다. 특정 상황에서 ACE의 value라고 생각하면 됩니다. */
        private static final int JOKER_VALUE = 11;
        private static final int BLACKJACK = 21;

        @Override
        public int calculate(int sum) {
            if (cabBeJoker(sum)) {
                return JOKER_VALUE;
            }
            return ACE.value;
        }

        private boolean cabBeJoker(int sum) {
            return sum <= BLACKJACK - JOKER_VALUE;
        }
    }
}
