package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {

    private static final double BLACKJACK_BONUS = 1.5;
    private final Map<Challenger, Result> resultStatistics = new LinkedHashMap<>();

    public ResultStatistics(final Challengers challengers, final Dealer dealer) {
        challengers.getList()
                .forEach(challenger -> resultStatistics.put(challenger, challenger.getChallengerResult(dealer)));
    }

//    public Map<Challenger, Result> getResultStatistics() {
//        return resultStatistics;
//    }

    public double getChallengerProfit(final Challenger challenger) {
        if (resultStatistics.get(challenger) == Result.BLACKJACK) {
            return challenger.getBetMoney() * BLACKJACK_BONUS;
        }
        if (resultStatistics.get(challenger) == Result.WIN) {
            return challenger.getBetMoney();
        }
        if (resultStatistics.get(challenger) == Result.LOSE) {
            return -challenger.getBetMoney();
        }
        return 0;
    }

    public int getDealerProfit() {
        return (int) (getDealerWinProfit() + getDealerLoss() + getDealerBlackJackLoss());
    }

    private int getDealerWinProfit() {
        return calculateDealerResult(Result.LOSE);
    }

    private int getDealerLoss() {
        return -calculateDealerResult(Result.WIN);
    }

    private double getDealerBlackJackLoss() {
        return -(calculateDealerResult(Result.BLACKJACK) * BLACKJACK_BONUS);
    }

    private int calculateDealerResult(final Result result) {
        return resultStatistics.entrySet()
                .stream()
                .filter(challengerResult -> challengerResult.getValue() == result)
                .mapToInt(challenger -> challenger.getKey().getBetMoney())
                .sum();
    }
}
