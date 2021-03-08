package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {

    private final Map<Challenger, Result> resultStatistics = new LinkedHashMap<>();

    public ResultStatistics(final Challengers challengers, final Dealer dealer) {
        challengers.getChallengersList()
                .forEach(challenger -> resultStatistics.put(challenger, challenger.getChallengerResult(dealer)));
    }

    public Map<Challenger, Result> getResultStatistics() {
        return resultStatistics;
    }

    public int getDealerWins() {
        return calculateDealerResult(Result.LOSE);
    }

    public int getDealerDraws() {
        return calculateDealerResult(Result.DRAW);
    }

    public int getDealerLoses() {
        return calculateDealerResult(Result.WIN);
    }

    private int calculateDealerResult(final Result result) {
        return Collections.frequency(resultStatistics.values(), result);
    }
}
