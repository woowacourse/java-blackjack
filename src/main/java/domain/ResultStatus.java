package domain;

import java.util.HashMap;
import java.util.Map;

public enum ResultStatus {
    WIN,
    LOSE,
    PUSH;

    public static Map<Participant, ResultStatus> judgeGameResult(Participants participants) {
        Map<Participant, ResultStatus> result = new HashMap<>();

        Map<Participant, Integer> totalRankSumByPlayer = participants.getTotalRankSumByPlayer();
        for (Participant player : totalRankSumByPlayer.keySet()) {
            judgeGameResultByPlayer(participants.findDealer(), player, result);
        }
        return result;
    }

    private static void judgeGameResultByPlayer(Participant dealer, Participant player, Map<Participant, ResultStatus> result) {
        if (player.isBust()) {
            result.put(player, ResultStatus.LOSE);
            return;
        }
        if (dealer.isBust()) {
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
