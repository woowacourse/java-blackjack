package blackjack.domain;

import java.util.Arrays;

public enum Score {
    WIN("승") {
        @Override
        public boolean match(int competeNumber) {
            return competeNumber > 0;
        }

        @Override
        public Score inverse() {
            return LOSE;
        }
    },
    DRAW("무") {
        @Override
        public boolean match(int competeNumber) {
            return competeNumber == 0;
        }

        @Override
        public Score inverse() {
            return DRAW;
        }
    },
    LOSE("패") {
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

    Score(String value) {
        this.value = value;
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
}
