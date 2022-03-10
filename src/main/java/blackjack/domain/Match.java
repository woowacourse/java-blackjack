package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

public enum Match {
    WIN("승", "패", Match::winPlayerCondition),
    LOSE("패", "승", Match::losePlayerCondition),
    DRAW("무", "무", Objects::equals),
    ;

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
                .orElseThrow();
    }

    private boolean findOppositeResult(Match match) {
        return match.result.equals(this.oppositeResult);
    }

    private static boolean winPlayerCondition(Integer playerPoint, Integer dealerPoint) {
        if (playerPoint > dealerPoint && playerPoint <= 21) {
            return true;
        }
        if (dealerPoint > 21 && playerPoint <= 21) {
            return true;
        }
        return false;
    }

    private static boolean losePlayerCondition(Integer playerPoint, Integer dealerPoint) {
        if (playerPoint < dealerPoint && dealerPoint <= 21) {
            return true;
        }
        if (playerPoint > 21) {
            return true;
        }
        return false;
    }
}
