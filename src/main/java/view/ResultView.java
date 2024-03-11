package view;

import static domain.game.Result.DRAW;
import static domain.game.Result.LOSE;
import static domain.game.Result.WIN;

import domain.game.Game;
import domain.game.PlayerResults;
import domain.game.Result;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    private static final Integer NONE = 0;
    private final Map<Result, String> resultViewer;

    public ResultView() {
        this.resultViewer = new EnumMap<>(Result.class);
        resultViewer.put(WIN, "승");
        resultViewer.put(DRAW, "무");
        resultViewer.put(LOSE, "패");
    }

    public void printResult(Game game) {
        System.out.println("\n## 최종 승패");
        PlayerResults playerResults = game.generatePlayerResults();
        Map<Result, Integer> dealerResult = playerResults.generateDealerResult();
        System.out.println("딜러: " + showDealerResult(dealerResult));

        playerResults.getPlayerResults()
                .forEach(((player, result) ->
                        System.out.println(player.getName().value() + ": " + show(result))));
    }

    private String showDealerResult(Map<Result, Integer> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > NONE)
                .map(entry -> entry.getValue() + show(entry.getKey()))
                .collect(Collectors.joining(" "));
    }

    private String show(Result result) {
        return resultViewer.get(result);
    }
}
