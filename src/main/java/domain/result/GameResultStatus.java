package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import static domain.BlackjackGame.BLACKJACK_SCORE;

public enum GameResultStatus {

    WIN("승", (standardTarget, comparisonTarget) -> standardTarget <= BLACKJACK_SCORE
            && (comparisonTarget > BLACKJACK_SCORE || (comparisonTarget < standardTarget))),
    PUSH("무", (standardTarget, comparisonTarget) -> (comparisonTarget > BLACKJACK_SCORE
            && standardTarget > BLACKJACK_SCORE) || (comparisonTarget == standardTarget)),
    LOSE("패", (standardTarget, comparisonTarget) -> comparisonTarget > standardTarget);


    private final String value;
    private final BiFunction<Integer, Integer, Boolean> match;

    GameResultStatus(String value, BiFunction<Integer, Integer, Boolean> match) {
        this.value = value;
        this.match = match;
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
