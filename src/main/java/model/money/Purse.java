package model.money;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.user.Dealer;
import model.user.Player;

public class Purse {

    private final Map<Player, Money> purses;

    private Purse(final Map<Player, Money> purses) {
        this.purses = purses;
    }

    public static Purse create(final List<Player> players) {
        final Map<Player, Money> purses = new LinkedHashMap<>();
        for (Player player : players) {
            purses.put(player, Money.zero());
        }
        return new Purse(purses);
    }

    public void addMoney(final Player player, final Money money) {
        final Money currentMoney = purses.get(player);
        final Money addedMoney = currentMoney.add(money);
        purses.put(player, addedMoney);
    }

    public void calculateMoney(Player player, Dealer dealer) {
        final Money money = getMoney(player);
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return;
        }

        if (player.isBlackJack()) {
            purses.put(player, money.blackJack());
        }
    }

    public Money getMoney(final Player player) {
        return purses.get(player);
    }
}
