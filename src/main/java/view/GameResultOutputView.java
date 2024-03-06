package view;

import domain.GameResult;
import dto.DealerGameResultDTO;
import dto.GameResultDTO;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResultOutputView {
    public static void print(GameResultDTO gameResultDTO) {
        String gamerName = gameResultDTO.getGamerName();
        GameResult gameResult = gameResultDTO.getGameResult();
        System.out.printf("%s: %s\n", gamerName, mapToString(gameResult));
    }

    public static void print(DealerGameResultDTO dealerGameResultDTO) {
        Map<GameResult, Integer> dealerGameResultCounts = dealerGameResultDTO.getDealerGameResultCounts();
        String output = dealerGameResultCounts.entrySet()
                .stream()
                .map(gameResultIntegerEntry ->
                        gameResultIntegerEntry.getValue() + mapToString(gameResultIntegerEntry.getKey()))
                .collect(Collectors.joining(" "));
        System.out.printf("딜러: %s\n", output);
    }

    private static String mapToString(GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return "승";
        }
        if (gameResult == GameResult.LOSE) {
            return "패";
        }
        if (gameResult == GameResult.TIE) {
            return "무";
        }
        throw new IllegalArgumentException("없는 승패 입니다.");
    }
}
