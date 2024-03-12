package view.viewer;

import domain.game.GameResult;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResultViewer {
    private static final Integer NONE = 0;
    private static final Map<GameResult, String> RESULT_VIEWER = Arrays.stream(GameResult.values())
            .collect(Collectors.toMap(
                    Function.identity(),
                    GameResultViewer::makeShow
            ));

    private static String makeShow(GameResult gameResult) {
        return switch (gameResult) {
            case WIN -> "승";
            case DRAW -> "무";
            case LOSE -> "패";
        };
    }

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
