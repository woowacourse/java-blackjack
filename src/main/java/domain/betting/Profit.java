package domain.betting;

import domain.participant.Name;
import domain.participant.Players;
import domain.result.Result;
import domain.result.Versus;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profit {

    private static final double BLACK_JACK_BONUS_PROFIT_RATE = 1.5;
    private static final int DEFAULT_PROFIT_RATE = 1;

    private final Map<Name, Double> profit;

    public Profit(Map<Name, Double> profit) {
        this.profit = Map.copyOf(profit);
    }

    public static Profit generateProfits(Result result, BettingReceipt bettingReceipt, Players players) {
        Map<Name, Double> profits = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            profits.put(name, calculatePlayerProfit(
                    result.getVersusOfPlayer(name),
                    bettingReceipt.getBettingMoney(name),
                    players.isBlackJackByName(name))
            );
        }
        return new Profit(profits);
    }

    private static double calculatePlayerProfit(Versus versusOfPlayer, BettingMoney bettingMoney, boolean isBlackJack) {
        if (versusOfPlayer == Versus.WIN) {
            return bettingMoney.getBettingMoney() * getBlackJackBonusRate(isBlackJack);
        }
        if (versusOfPlayer == Versus.LOSE) {
            return bettingMoney.getBettingMoney() * (-1);
        }
        return 0;
    }

    private static double getBlackJackBonusRate(boolean isBlackJack) {
        if (isBlackJack) {
            return BLACK_JACK_BONUS_PROFIT_RATE;
        }
        return DEFAULT_PROFIT_RATE;
    }

    public double getProfit(Name name) {
        return profit.get(name);
    }

    public double calculateDealerProfit() {
        return profit.keySet().stream()
                .mapToDouble(profit::get)
                .sum() * (-1);
    }
}
