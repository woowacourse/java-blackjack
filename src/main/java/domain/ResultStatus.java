package domain;

import java.util.HashMap;
import java.util.Map;

public enum ResultStatus {
    WIN("승"),
    LOSE("패"),
    PUSH("무승부")
    ;

    private final String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public static Map<Player, ResultStatus> judgeGameResult(Players players, Dealer dealer) {
        Map<Player, ResultStatus> result = new HashMap<>();

        Map<Player, Integer> totalNumberSumByName = players.getTotalNumberSumByName();
        for (Player player : totalNumberSumByName.keySet()) {
            if (player.checkExceedTwentyOne()) {
                result.put(player, ResultStatus.LOSE);
                continue;
            }

            if (dealer.checkExceedTwentyOne()) {
                result.put(player, ResultStatus.WIN);
                continue;
            }

            int dealerAbs = dealer.calculateDifferenceFromTwentyOne();
            int playerAbs = player.calculateDifferenceFromTwentyOne();
            if (playerAbs > dealerAbs) {
                result.put(player, ResultStatus.LOSE);
                continue;
            }

            if (playerAbs == dealerAbs) {
                result.put(player, ResultStatus.PUSH);
                continue;
            }

            result.put(player, ResultStatus.WIN);

        }
        return result;
    }

    public static Map<ResultStatus, Integer> initMap() {
        Map<ResultStatus, Integer> counts = new HashMap<>();
        for (ResultStatus value : values()) {
            counts.put(value, 0);
        }
        return counts;
    }
}
