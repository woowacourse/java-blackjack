package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN("승", GameResult::isDealerWin),
    DRAW("무", GameResult::isDraw),
    LOSE("패", GameResult::isDealerLose),
    ;

    private static final int BURST = 21;

    private final String result;
    private final BiPredicate<Integer, Integer> condition;

    GameResult(final String result, final BiPredicate<Integer, Integer> condition) {
        this.result = result;
        this.condition = condition;
    }

    public static GameResult of(final int dealerScore, final int gamblerScore) {
        if (containsBurst(dealerScore, gamblerScore)) {
            return getBurstResult(dealerScore, gamblerScore);
        }
        return Arrays.stream(values())
            .filter(it -> it.condition.test(dealerScore, gamblerScore))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 점수가 입력되었습니다."));
    }

    private static boolean containsBurst(final int dealerScore, final int gamblerScore) {
        return dealerScore > BURST || gamblerScore > BURST;
    }

    private static GameResult getBurstResult(final Integer dealerScore, final Integer gamblerScore) {
        if (dealerScore > BURST && gamblerScore > BURST) {
            return DRAW;
        }
        if (dealerScore <= BURST && gamblerScore > BURST) {
            return WIN;
        }
        return LOSE;
    }

    private static boolean isDealerWin(final Integer dealerScore, final Integer gamblerScore) {
        return dealerScore > gamblerScore;
    }

    private static boolean isDraw(final Integer dealerScore, final Integer gamblerScore) {
        return dealerScore == gamblerScore;
    }

    private static boolean isDealerLose(final Integer dealerScore, final Integer gamblerScore) {
        return dealerScore < gamblerScore;
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
