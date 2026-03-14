package domain.betting;

import domain.gamer.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private static final int PREFIX_INITIAL_PROFIT = 0;

    private final Map<PlayerName, BettingMoney> bettingMoneyByPlayer;
    private final Map<PlayerName, Profit> profitByPlayer;

    public Bettings() {
        this.profitByPlayer = new HashMap<>();
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public void bet(PlayerName playerName, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.put(playerName, bettingMoney);
    }

    public BettingMoney getPlayerBettingMoney(PlayerName playerName) {
        return bettingMoneyByPlayer.get(playerName);
    }

    public void settleBettingMoney(PlayerName playerName, double bettingRate) {
        BettingMoney bettingMoney = bettingMoneyByPlayer.get(playerName);
        profitByPlayer.put(playerName, bettingMoney.withRate(bettingRate));
    }

    public Profit calculateDealerProfit() {
        return profitByPlayer.values()
                .stream()
                .reduce(new Profit(PREFIX_INITIAL_PROFIT), Profit::addProfit)
                .reverseProfit();
    }

    public Profit getPlayerProfit(PlayerName playerName) {
        return profitByPlayer.get(playerName);
    }

}
