package blackjack.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum GameOutcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String printValue;

    GameOutcome(final String printValue) {
        this.printValue = printValue;
    }

    public static GameOutcome calculateOutcome(final int score, final int compareScore) {
        if (score > compareScore) {
            return WIN;
        } else if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static Map<GameOutcome, Integer> createInitMap() {
        Map<GameOutcome, Integer> results = new EnumMap<>(GameOutcome.class);
        Arrays.stream(values())
            .forEach(key -> results.merge(key, 0, (x, y) -> y));
        return results;
    }

    public GameOutcome reverse() {
        if (this == WIN) {
            return LOSE;
        } else if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getPrintValue() {
        return printValue;
    }
}
