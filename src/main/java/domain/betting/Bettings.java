package domain.betting;

import domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private static final int DEALER_INITIAL_PROFIT = 0;

    private final Map<Player, Money> bettingMoneyByPlayer;

    public Bettings() {
        this.bettingMoneyByPlayer = new LinkedHashMap<>();
    }

    public void bet(Player player, Money money) {
        bettingMoneyByPlayer.put(player, money);
    }

    public Money getPlayerBettingMoney(Player player) {
        return bettingMoneyByPlayer.get(player);
    }

    public Money calculateBettingMoney(Player player, BettingRate bettingRate) {
        Money money = bettingMoneyByPlayer.get(player);
        return bettingMoneyByPlayer.put(player, bettingRate.payOut(money));
    }

    public Money calculateDealerProfit() {
        return bettingMoneyByPlayer.values()
                .stream()
                .reduce(Money.from(DEALER_INITIAL_PROFIT), Money::addMoney)
                .reverseMoney();
    }

}
