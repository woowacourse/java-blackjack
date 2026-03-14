package domain.betting;

import domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private static final int PREFIX_INITIAL_PROFIT = 0;

    private final Map<Player, BettingMoney> bettingMoneyByPlayer;
    private final Map<Player, Profit> profitByPlayer;

    public Bettings() {
        this.profitByPlayer = new HashMap<>();
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public void bet(Player player, BettingMoney bettingMoney) {
        // TODO 베팅 두번 제한 로직
        bettingMoneyByPlayer.put(player, bettingMoney);
    }

    public BettingMoney getPlayerBettingMoney(Player player) {
        return bettingMoneyByPlayer.get(player);
    }

    public void settleBettingMoney(Player player, double bettingRate) {
        BettingMoney bettingMoney = bettingMoneyByPlayer.get(player);
        profitByPlayer.put(player, bettingMoney.withRate(bettingRate));
    }

    public Profit calculateDealerProfit() {
        return profitByPlayer.values()
                .stream()
                .reduce(new Profit(PREFIX_INITIAL_PROFIT), Profit::addProfit)
                .reverseProfit();
    }

    public Profit getPlayerProfit(Player player) {
        return profitByPlayer.get(player);
    }

}
