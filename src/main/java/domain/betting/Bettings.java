package domain.betting;

import domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private static final int DEALER_INITIAL_PROFIT = 0;

    private final Map<Player, Money> bettings;

    public Bettings() {
        this.bettings = new LinkedHashMap<>();
    }

    public void bet(Player player, Money money) {
        bettings.put(player, money);
    }

    public Money getPlayerBettingMoney(Player player) {
        return bettings.get(player);
    }

    public Money calculateBettingMoney(Player player, BettingRate bettingRate) {
        Money money = bettings.get(player);
        return bettings.put(player, bettingRate.payOut(money));
    }

    public Money calculateDealerProfit() {
        return bettings.values()
                .stream()
                .reduce(Money.from(DEALER_INITIAL_PROFIT), Money::addMoney)
                .reverseMoney();
    }

}
