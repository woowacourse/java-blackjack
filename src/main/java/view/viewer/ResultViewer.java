package view.viewer;

import static domain.game.Result.DRAW;
import static domain.game.Result.LOSE;
import static domain.game.Result.WIN;

import domain.game.Result;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultViewer {
    private static final Integer NONE = 0;
    private static final Map<Result, String> RESULT_VIEWER = Map.of(WIN, "승", DRAW, "무", LOSE, "패");

    public static String showDealerResult(Map<Result, Integer> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > NONE)
                .map(entry -> entry.getValue() + show(entry.getKey()))
                .collect(Collectors.joining(" "));
    }

    public static String show(Result result) {
        return RESULT_VIEWER.get(result);
    }
}
