package domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public GameResultDto createGameResult(int dealerScore, Map<String, Integer> scoreByPlayer) {
        EnumMap<MatchResult, Integer> dealerMatchResult = new EnumMap<>(MatchResult.class);
        Map<String, MatchResult> playerMatchResults = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : scoreByPlayer.entrySet()) {
            if (isBust(entry.getValue())) {
                // 딜러 맵 승 증가
                dealerMatchResult.merge(MatchResult.WIN, 1, Integer::sum);
                // 플레이어맵 put(entry.getKey(), matchResult.패)
                playerMatchResults.put(entry.getKey(), MatchResult.LOSE);
                continue;
            }
            if (isBust(dealerScore)) {
                // 딜러 맵 패 증가
                dealerMatchResult.merge(MatchResult.LOSE, 1, Integer::sum);
                // 플레이어맵 put(entry.getKey(), matchResult.승)
                playerMatchResults.put(entry.getKey(), MatchResult.WIN);
                continue;
            }

            // 비교
            int dealerGap = Math.abs(21 - dealerScore);
            int playerGap = Math.abs(21 - entry.getValue());
            if (dealerGap < playerGap) {
                // 딜러 맵 승 증가
                dealerMatchResult.merge(MatchResult.WIN, 1, Integer::sum);
                // 플레이어맵 put(entry.getKey(), matchResult.패)
                playerMatchResults.put(entry.getKey(), MatchResult.LOSE);
                continue;
            }

            if (dealerGap == playerGap) {
                dealerMatchResult.merge(MatchResult.DRAW, 1, Integer::sum);
                playerMatchResults.put(entry.getKey(), MatchResult.DRAW);
                continue;
            }

            dealerMatchResult.merge(MatchResult.LOSE, 1, Integer::sum);
            // 플레이어맵 put(entry.getKey(), matchResult.승)
            playerMatchResults.put(entry.getKey(), MatchResult.WIN);
        }

        return new GameResultDto(dealerMatchResult, playerMatchResults);
    }

    private boolean isBust(int score) {
        return score > 21;
    }
}
