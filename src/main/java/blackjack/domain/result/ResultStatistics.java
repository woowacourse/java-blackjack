package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {

    private Map<Challenger, Result> resultStatistics = new LinkedHashMap<>();

    public ResultStatistics(final Challengers challengers, final Dealer dealer) {
        challengers.getPlayers()
                .forEach(challenger -> resultStatistics.put(challenger, challenger.getChallengerResult(dealer)));
    }

    public int getChallengerResult(final Result result) {
        return Collections.frequency(resultStatistics.values(), result);
    }

    public int getDealerWins() {
        return getChallengerResult(Result.LOSE);
    }

    public int getDealerDraws() {
        return getChallengerResult(Result.DRAW);
    }

    public int getDealerLoses() {
        return getChallengerResult(Result.WIN);
    }
}
