package model.judgement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Player;

public record PlayerResult(Map<Player, ResultStatus> result) {

    public PlayerResult(Map<Player, ResultStatus> result) {
        this.result = Collections.unmodifiableMap(new LinkedHashMap<>(result));
    }

    public DealerResult calculateDealerResult() {
        int dealerWinCount = countByStatus(ResultStatus.LOSE);
        int dealerLoseCount = countByStatus(ResultStatus.WIN) + countByStatus(ResultStatus.BLACKJACK);
        int dealerDrawCount = countByStatus(ResultStatus.DRAW);
        return new DealerResult(dealerWinCount, dealerLoseCount, dealerDrawCount);
    }

    private int countByStatus(ResultStatus resultStatus) {
        return (int) result.values()
                .stream()
                .filter(status -> status == resultStatus)
                .count();
    }
}
