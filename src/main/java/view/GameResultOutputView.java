package view;

import domain.blackjack.GameResult;
import dto.DealerGameResultDTO;
import dto.PlayerGameResultDTO;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResultOutputView {
    public static void print(PlayerGameResultDTO playerGameResultDTO) {
        String gamerName = playerGameResultDTO.getGamerName();
        String gameResult = mapToString(playerGameResultDTO.getGameResult());
        System.out.printf("%s: %s\n", gamerName, gameResult);
    }

    public static void print(DealerGameResultDTO dealerGameResultDTO) {
        Map<GameResult, Integer> dealerGameResultCounts = dealerGameResultDTO.getDealerGameResultCounts();
        String dealersGameResultOutput = dealerGameResultCounts.entrySet()
                .stream()
                .map(mapEntry -> mapEntry.getValue() + mapToString(mapEntry.getKey()))
                .collect(Collectors.joining(" "));
        System.out.printf("딜러: %s\n", dealersGameResultOutput);
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
