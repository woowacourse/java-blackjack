package blackjack.domain.result;

import java.util.stream.Stream;

public enum ResultEnum {
    WIN("승") {
        @Override
        boolean condition(int score, int opponentScore) {
            return score > opponentScore;
        }
    },
    DRAW("무") {
        @Override
        boolean condition(int score, int opponentScore) {
            return score == opponentScore;
        }
    },
    LOSE("패") {
        @Override
        boolean condition(int score, int opponentScore) {
            return score < opponentScore;
        }
    },
    FAILURE("승부 조회 실패") {
        @Override
        boolean condition(int score, int opponentScore) {
            return false;
        }
    };

    private final String result;

    ResultEnum(final String result) {
        this.result = result;
    }

    abstract boolean condition(final int score, final int opponentScore);

    public static ResultEnum checkResult(final int score, final int opponentScore) {
        return Stream.of(values())
                .filter(resultEnum -> resultEnum.condition(score, opponentScore))
                .findAny()
                .orElse(FAILURE);
    }
}
