package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.*;
import java.util.function.Function;

public class Betting {

    private final Map<Player, BettingMoney> bettingMap;

    private Betting(Map<Player, BettingMoney> bettingMap) {
        this.bettingMap = bettingMap;
    }

    public static Betting of(List<Player> players, Function<Player, Integer> moneyProvider) {
        Map<Player, BettingMoney> bettingMap = new HashMap<>();
        for (Player player : players) {
            int amount = moneyProvider.apply(player);
            bettingMap.put(player, new BettingMoney(amount));
        }
        return new Betting(bettingMap);
    }

    public Map<Player, BettingMoney> getBettingMap() {
        return Collections.unmodifiableMap(bettingMap);
    }
}
