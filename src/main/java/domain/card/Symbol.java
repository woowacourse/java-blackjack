package domain.card;

public enum Symbol {
    ACE(1, "A", new AceCalculator()),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int value;
    private final String pattern;
    private final SymbolCalculator calculator;

    Symbol(int value, String pattern) {
        this.value = value;
        this.pattern = pattern;
        this.calculator = new DefaultSymbolCalculator(pattern);
    }

    Symbol(int value, String pattern, SymbolCalculator calculator) {
        this.value = value;
        this.pattern = pattern;
        this.calculator = calculator;
    }

    int calculate(int sum) {
        return calculator.calculate(sum);
    }

    int getValue() {
        return value;
    }

    String getPattern() {
        return pattern;
    }

    boolean isAce() {
        return this.equals(Symbol.ACE);
    }

    private interface SymbolCalculator {
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

    private static class DefaultSymbolCalculator implements SymbolCalculator {
        private String pattern;

        private DefaultSymbolCalculator(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public int calculate(int sum) {
            Symbol symbol = findByPattern(pattern);
            return symbol.getValue();
        }
    }

    private static Symbol findByPattern(String pattern) {
        for (Symbol symbol : values()) {
            if (symbol.getPattern().equals(pattern)) {
                return symbol;
            }
        }

        throw new IllegalArgumentException("해당 심볼을 찾을 수 없습니다.");
    }
}
