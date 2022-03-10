package util;

import java.util.EnumMap;
import java.util.Map;
import model.Result;

public class ResultConvertor {
    private static final Map<Result, String> results = new EnumMap<>(Result.class);

    static {
        initResults();
    }

    private static void initResults() {
        results.put(Result.WIN, "승");
        results.put(Result.LOSE, "패");
        results.put(Result.DRAW, "무");
    }

    public static String convert(Result result) {
        return results.get(result);
    }
}
