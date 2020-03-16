package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

public enum ResultType {
    WIN("승"),
    DRAW("무승부"),
    LOSE("패");

    private static Map<ResultType, ResultType> CACHE_FOR_REVERSE;
    private String name;

    static {
        CACHE_FOR_REVERSE = new HashMap<>();
        CACHE_FOR_REVERSE.put(WIN, LOSE);
        CACHE_FOR_REVERSE.put(DRAW, DRAW);
        CACHE_FOR_REVERSE.put(LOSE, WIN);
    }

    ResultType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ResultType reverse(ResultType result) {
        return CACHE_FOR_REVERSE.get(result);
    }
}
