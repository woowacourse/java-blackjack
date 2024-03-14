package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public enum GameResultStatus {

    WIN("승", (standardTarget, comparisonTarget) -> standardTarget <= BLACKJACK_SCORE
            && (comparisonTarget > BLACKJACK_SCORE || (comparisonTarget < standardTarget)), 1),
    PUSH("무", (standardTarget, comparisonTarget) -> (comparisonTarget > BLACKJACK_SCORE
            && standardTarget > BLACKJACK_SCORE) || (comparisonTarget == standardTarget), 0),
    LOSE("패", (standardTarget, comparisonTarget) -> comparisonTarget > standardTarget, -1);


    private final String value;
    private final BiFunction<Integer, Integer, Boolean> match;
    private final double ratio;

    GameResultStatus(String value, BiFunction<Integer, Integer, Boolean> match, double ratio) {
        this.value = value;
        this.match = match;
        this.ratio = ratio;
    }

    public static GameResultStatus comparedTo(Integer standardTarget, Integer comparisonTarget) {
        return Arrays.stream(values())
                     .filter(status -> status.match.apply(standardTarget, comparisonTarget))
                     .findFirst()
                     .orElse(LOSE);
    }

    public String getValue() {
        return value;
    }

}
