package domain;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private final Players players;
    private final Dealer dealer;

    public GameManager(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Map<String, ResultStatus> judgeGameResult() {
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
}
