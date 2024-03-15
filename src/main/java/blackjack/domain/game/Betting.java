package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.*;
import java.util.function.Function;

public class Betting {

    private final Map<Player, BlackjackMoney> bettingMap;

    private Betting(Map<Player, BlackjackMoney> bettingMap) {
        this.bettingMap = bettingMap;
    }

    public static Betting of(List<Player> players, Function<Player, Integer> moneyProvider) {
        Map<Player, BlackjackMoney> bettingMap = new HashMap<>();
        for (Player player : players) {
            int money = moneyProvider.apply(player);
            if (money <= 0) {
                throw new IllegalArgumentException("배팅 액수는 양수여야 합니다.");
            }
            bettingMap.put(player, new BlackjackMoney(money));
        }
        return new Betting(bettingMap);
    }

    public Map<Player, BlackjackMoney> getBettingMap() {
        return Collections.unmodifiableMap(bettingMap);
    }
}
