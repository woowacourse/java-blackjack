package blackjack.domain.game;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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
        return Arrays.stream(values())
                .collect(Collectors.toMap(value -> value, value -> 0));
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
