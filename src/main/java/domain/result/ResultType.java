package domain.result;

import java.util.HashMap;
import java.util.Map;

public enum ResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private static Map<ResultType, ResultType> opposite;

    static {
        opposite = new HashMap<>();
        opposite.put(ResultType.WIN, ResultType.LOSE);
        opposite.put(ResultType.DRAW, ResultType.DRAW);
        opposite.put(ResultType.LOSE, ResultType.WIN);
    }

    private final String result;

    ResultType(String result) {
        this.result = result;
    }

    public ResultType getOppositeResult() {
        return opposite.get(this);
    }

    public String getResult() {
        return result;
    }
}
