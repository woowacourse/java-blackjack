package blackjack.domain.machine;

import static blackjack.domain.participant.Guest.LIMIT_POINT;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;

public enum Match {
    WIN("승", "패", Match::winPlayerCondition),
    LOSE("패", "승", Match::losePlayerCondition),
    DRAW("무", "무", Objects::equals),
    ;

    private static final String NOTHING_MATCH_RESULT_MESSAGE = "결과를 찾을 수 없습니다.";

    private final String result;
    private final String oppositeResult;
    private final BiFunction<Integer, Integer, Boolean> expression;

    Match(String result, String oppositeResult, BiFunction<Integer, Integer, Boolean> expression) {
        this.result = result;
        this.oppositeResult = oppositeResult;
        this.expression = expression;
    }

    public static Match findWinner(int playerPoint, int dealerPoint) {
        return Arrays.stream(Match.values())
                .filter(match -> match.expression.apply(playerPoint, dealerPoint))
                .findFirst()
                .orElse(LOSE);
    }

    public String getResult() {
        return result;
    }

    public Match getDealerResult() {
        return Arrays.stream(values())
                .filter(this::findOppositeResult)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(NOTHING_MATCH_RESULT_MESSAGE));
    }

    private boolean findOppositeResult(Match match) {
        return match.result.equals(this.oppositeResult);
    }

    private static boolean winPlayerCondition(Integer playerPoint, Integer dealerPoint) {
        if (playerPoint > dealerPoint && playerPoint <= LIMIT_POINT) {
            return true;
        }
        return dealerPoint > LIMIT_POINT && playerPoint <= LIMIT_POINT;
    }

    private static boolean losePlayerCondition(Integer playerPoint, Integer dealerPoint) {
        if (playerPoint < dealerPoint && dealerPoint <= LIMIT_POINT) {
            return true;
        }
        return playerPoint > LIMIT_POINT;
    }
}
