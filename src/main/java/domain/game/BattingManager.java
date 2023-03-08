package domain.game;

import domain.player.Gambler;
import domain.player.GamblerCompeteResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BattingManager {

    private final Map<Gambler, BattingMoney> battingMoneyMap;

    public BattingManager(final Map<Gambler, BattingMoney> battingMoneyMap) {
        this.battingMoneyMap = new HashMap<>(battingMoneyMap);
    }

    public Map<Gambler, Revenue> revenue(final Map<Gambler, GamblerCompeteResult> gamblerCompeteResults) {
        return battingMoneyMap.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), gambler -> calculateRevenue(gambler, gamblerCompeteResults)));
    }

    private Revenue calculateRevenue(final Gambler gambler, final Map<Gambler, GamblerCompeteResult> gamblerCompeteResults) {
        if (gamblerCompeteResults.get(gambler).isWin()) {
            if (gambler.isBlackJack()) {
                return Revenue.blackJackWin(battingMoneyMap.get(gambler));
            }
            return Revenue.defaultWin(battingMoneyMap.get(gambler));
        }

        if (gamblerCompeteResults.get(gambler).isLose()) {
            return Revenue.lose(battingMoneyMap.get(gambler));
        }
        return Revenue.draw();
    }

    public static class Revenue {

        private final double amount;

        public Revenue(final double amount) {
            this.amount = amount;
        }

        public static Revenue lose(final BattingMoney money) {
            return new Revenue(money.amount() * -1);
        }

        public static Revenue defaultWin(final BattingMoney money) {
            return new Revenue(money.amount());
        }

        public static Revenue draw() {
            return new Revenue(0);
        }

        public static Revenue blackJackWin(final BattingMoney money) {
            return new Revenue(money.amount() * 1.5);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Revenue)) return false;
            final Revenue revenue = (Revenue) o;
            return Double.compare(revenue.amount, amount) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }

        public double amount() {
            return amount;
        }
    }
}
