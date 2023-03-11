package model.money;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.State;
import model.user.Dealer;
import model.user.Player;

public class Purse {

    private final Map<Player, Bet> purses;

    private Purse(final Map<Player, Bet> purses) {
        this.purses = purses;
    }

    public static Purse create(final List<Player> players) {
        final Map<Player, Bet> purses = new LinkedHashMap<>();
        for (Player player : players) {
            purses.put(player, Bet.zero());
        }
        return new Purse(purses);
    }

    public void addMoney(final Player player, final Bet bet) {
        final Bet currentBet = purses.get(player);
        final Bet addedBet = currentBet.add(bet);
        purses.put(player, addedBet);
    }

    public void calculateMoneyAll(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            calculateMoney(player, dealer);
        }
    }

    private void calculateMoney(final Player player, final Dealer dealer) {
        final State state = player.judgeResult(dealer);
        final Bet bet = findMoneyByPlayer(player);
        purses.put(player, bet.calculateBet(state));
    }

    public Bet findMoneyByPlayer(final Player player) {
        return purses.get(player);
    }

    public Map<Player, Bet> getPursesAll() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(purses));
    }

    public long getTotalBet() {
        return purses.values().stream()
                .mapToLong(Bet::getMoney)
                .sum();
    }
}
