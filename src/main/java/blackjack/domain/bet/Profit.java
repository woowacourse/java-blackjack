package blackjack.domain.bet;

import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.Grade;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Profit {

    private final Map<Player, Betting> bettings = new HashMap<>();

    public void bet(final Player player, final Supplier<Betting> supplier) {
        bettings.put(player, createBetting(supplier));
    }

    public void calculate(final Result result) {
        for (Player player : bettings.keySet()) {
            Grade grade = result.getGrade(player);
            bettings.get(player).calculateProfit(grade);
        }
    }

    public int dealerProfit() {
        return -totalProfit();
    }

    private Betting createBetting(final Supplier<Betting> supplier) {
        return supplier.get();
    }

    private int totalProfit() {
        return bettings.values().stream()
                .map(Betting::getBetting)
                .reduce(0, Integer::sum);
    }

    public int getBetting(final Player player) {
        return bettings.get(player).getBetting();
    }
}
