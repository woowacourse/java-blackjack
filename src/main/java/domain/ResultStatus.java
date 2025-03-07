package domain;

import java.util.HashMap;
import java.util.Map;

public enum ResultStatus {
    WIN("승"),
    LOSE("패"),
    PUSH("무승부");

    private final String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public static Map<Player, ResultStatus> judgeGameResult(Players players, Dealer dealer) {
        Map<Player, ResultStatus> result = new HashMap<>();

        Map<Player, Integer> totalNumberSumByPlayer = players.getTotalNumberSumByPlayer();
        for (Player player : totalNumberSumByPlayer.keySet()) {
            judgeGameResultByPlayer(dealer, player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Dealer dealer, Player player, Map<Player, ResultStatus> result) {
        if (player.checkExceedTwentyOne()) {
            result.put(player, ResultStatus.LOSE);
            return;
        }
        if (dealer.checkExceedTwentyOne()) {
            result.put(player, ResultStatus.WIN);
            return;
        }
        compareDifference(dealer, player, result);
    }

    private static void compareDifference(Dealer dealer, Player player, Map<Player, ResultStatus> result) {
        int dealerAbs = dealer.calculateDifferenceFromTwentyOne();
        int playerAbs = player.calculateDifferenceFromTwentyOne();
        if (playerAbs > dealerAbs) {
            result.put(player, ResultStatus.LOSE);
            return;
        }
        if (playerAbs == dealerAbs) {
            result.put(player, ResultStatus.PUSH);
            return;
        }
        result.put(player, ResultStatus.WIN);
    }

    public static Map<ResultStatus, Integer> initMap() {
        Map<ResultStatus, Integer> counts = new HashMap<>();
        for (ResultStatus value : values()) {
            counts.put(value, 0);
        }
        return counts;
    }
}
