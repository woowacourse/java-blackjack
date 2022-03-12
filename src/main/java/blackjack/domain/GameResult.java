package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN("승", (dealerScore, playerScore) -> dealerScore > playerScore),
    DRAW("무", (dealerScore, playerScore) -> dealerScore == playerScore),
    LOSE("패", (dealerScore, playerScore) -> dealerScore < playerScore),
        ;

    private final String result;
    private final BiPredicate<Integer, Integer> condition;

    GameResult(final String result, final BiPredicate<Integer, Integer> condition) {
        this.result = result;
        this.condition = condition;
    }

    public static GameResult of(final int dealerScore, final int playerScore) {
        if (dealerScore > 21 && playerScore > 21) {
            return DRAW;
        }
        if (dealerScore > 21) {
           return LOSE;
        }
        if (playerScore > 21) {
            return WIN;
        }
        return Arrays.stream(values())
            .filter(it -> it.condition.test(dealerScore, playerScore))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 점수가 입력되었습니다."));
    }

    public GameResult reverse() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }

    public String getResult() {
        return result;
    }
}
