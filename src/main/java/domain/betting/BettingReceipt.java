package domain.betting;

import domain.participant.Name;
import domain.participant.Players;
import domain.result.Result;
import domain.result.Versus;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingReceipt {

    private final Map<Name, Money> bettingReceipt;

    public BettingReceipt(Map<Name, Money> bettingReceipt) {
        this.bettingReceipt = Map.copyOf(bettingReceipt);
    }

    public Profits generateProfits(Result result, Players players) {
        Map<Name, Double> profits = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            profits.put(name, calculatePlayerProfit(name, result, players.isBlackJackByName(name)));
        }
        return new Profits(profits);
    }

    private double calculatePlayerProfit(Name name, Result result, boolean isBlackJack) {
        int bettingMoney = bettingReceipt.get(name).getMoney();
        if (result.getVersusOfPlayer(name) == Versus.WIN) {
            return bettingMoney * getBlackJackBonusRate(isBlackJack);
        }
        if (result.getVersusOfPlayer(name) == Versus.LOSE) {
            return bettingMoney * (-1);
        }
        return 0;
    }

    private double getBlackJackBonusRate(boolean isBlackJack) {
        if (isBlackJack) {
            return 1.5;
        }
        return 1;
    }
}
