package blackjack.domain;

import java.util.Arrays;

public enum Score {
    WIN("승", 1.5) {
        @Override
        public boolean match(int competeNumber) {
            return competeNumber > 0;
        }

        @Override
        public Score inverse() {
            return LOSE;
        }
    },
    DRAW("무", 1) {
        @Override
        public boolean match(int competeNumber) {
            return competeNumber == 0;
        }

        @Override
        public Score inverse() {
            return DRAW;
        }
    },
    LOSE("패", -1) {
        @Override
        public boolean match(int competeNumber) {
            return competeNumber < 0;
        }

        @Override
        public Score inverse() {
            return WIN;
        }
    };

    private final String value;
    private final double dividendRate;


    Score(String value, double dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    abstract public boolean match(int competeNumber);
    abstract public Score inverse();

    public static Score compete(int playerTotalNumber, int dealerTotalNumber) {
        int competeNumber = playerTotalNumber - dealerTotalNumber;

        return Arrays.stream(values())
            .filter(score -> score.match(competeNumber))
            .findAny().orElse(Score.LOSE);
    }

    public String getValue(){
        return value;
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
