package blackjack.domain.blackjackgame;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultStatus {
    WIN("승", (score, otherScore) -> score > otherScore),
    DRAW("무", (score, otherScore) -> score == otherScore),
    LOSE("패", (score, otherScore) -> score < otherScore);

    private final String name;
    private final BiPredicate<Integer, Integer> condition;

    ResultStatus(final String name, final BiPredicate<Integer, Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static ResultStatus of(final int playerScore, final int dealerScore) {
        return Arrays.stream(values())
                .filter(resultStatus -> resultStatus.findResult(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private boolean findResult(final int playerScore, final int dealerScore) {
        return condition.test(playerScore, dealerScore);
    }

    public ResultStatus reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return this.name;
    }
}
