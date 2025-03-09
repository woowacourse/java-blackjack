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

    public static Map<Participant, ResultStatus> judgeGameResult(Participants participants) {
        Map<Participant, ResultStatus> result = new HashMap<>();

        Map<Participant, Integer> totalRankSumByPlayer = participants.getTotalRankSumByPlayer();
        for (Participant player : totalRankSumByPlayer.keySet()) {
            judgeGameResultByPlayer(participants.findDealer(), player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Participant dealer, Participant player, Map<Participant, ResultStatus> result) {
        if (player.isBurst()) {
            result.put(player, ResultStatus.LOSE);
            return;
        }
        if (dealer.isBurst()) {
            result.put(player, ResultStatus.WIN);
            return;
        }
        compareDifference(dealer, player, result);
    }

    private static void compareDifference(Participant dealer, Participant player, Map<Participant, ResultStatus> result) {
        int dealerAbs = dealer.calculateDifferenceFromBlackjackScore();
        int playerAbs = player.calculateDifferenceFromBlackjackScore();
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
