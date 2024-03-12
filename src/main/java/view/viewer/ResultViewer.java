package view.viewer;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.game.GameResult;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultViewer {
    private static final Integer NONE = 0;
    private static final Map<GameResult, String> RESULT_VIEWER = Map.of(WIN, "승", DRAW, "무", LOSE, "패");

    public static String showDealerResult(Map<GameResult, Integer> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > NONE)
                .map(entry -> entry.getValue() + show(entry.getKey()))
                .collect(Collectors.joining(" "));
    }

    public static String show(GameResult gameResult) {
        return RESULT_VIEWER.get(gameResult);
    }
}
