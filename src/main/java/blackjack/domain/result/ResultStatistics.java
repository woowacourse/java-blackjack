package blackjack.domain.result;

import blackjack.domain.participant.Challenger;
import blackjack.domain.participant.Challengers;
import blackjack.domain.participant.Dealer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {

    private final Map<Challenger, Result> resultStatistics = new LinkedHashMap<>();

    public ResultStatistics(final Challengers challengers, final Dealer dealer) {
        challengers.getChallengersAsList()
                .forEach(challenger -> resultStatistics.put(challenger, Result.getChallengerResult(challenger, dealer)));
    }

    public Map<Challenger, Result> getResultStatistics() {
        return resultStatistics;
    }

    public int getDealerWinCounts() {
        return calculateDealerResult(Result.LOSE);
    }

    public int getDealerDrawCounts() {
        return calculateDealerResult(Result.DRAW);
    }

    public int getDealerLoseCounts() {
        return calculateDealerResult(Result.WIN);
    }

    private int calculateDealerResult(final Result result) {
        return Collections.frequency(resultStatistics.values(), result);
    }
}
