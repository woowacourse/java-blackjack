package domain;

import java.util.HashMap;
import java.util.Map;

public enum ResultStatus {
    WIN("승"),
    LOSE("패"),
    PUSH("무승부")
    ;

    private String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public static Map<String, ResultStatus> judgeGameResult(Players players, Dealer dealer) {
        Map<String, ResultStatus> result = new HashMap<>();

        Map<String, Integer> totalNumberSumByName = players.getTotalNumberSumByName();
        int totalNumberSumOfDealer = dealer.getTotalNumberSum();
        for (String name : totalNumberSumByName.keySet()) {
            if (totalNumberSumByName.get(name) > 21) {
                result.put(name, ResultStatus.LOSE);
                continue;
            }

            if (dealer.checkExceedTwentyOne()) {
                result.put(name, ResultStatus.WIN);
                continue;
            }

            int dealerAbs = Math.abs(totalNumberSumOfDealer - 21);
            int playerAbs = Math.abs(totalNumberSumByName.get(name) - 21);
            if (playerAbs > dealerAbs) {
                result.put(name, ResultStatus.LOSE);
                continue;
            }

            if (playerAbs == dealerAbs) {
                result.put(name, ResultStatus.PUSH);
                continue;
            }

            result.put(name, ResultStatus.WIN);

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
