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

    public BlackjackMoney findMoneyOf(Player player) {
        if (bettingMap.containsKey(player)) {
            return bettingMap.get(player);
        }
        throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(bettingMap.keySet());
    }
}
