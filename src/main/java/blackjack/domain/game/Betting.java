package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Betting {

    private final Map<Player, Integer> bettingMap;

    public Betting() {
        this(new HashMap<>());
    }

    private Betting(Map<Player, Integer> bettingMap) {
        this.bettingMap = bettingMap;
    }

    public void bet(Player player, int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 액수는 양수여야 합니다.");
        }
        bettingMap.put(player, money);
    }

    public int findMoneyOf(Player player) {
        if (bettingMap.containsKey(player)) {
            return bettingMap.get(player);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어입니다.");
    }

    public Betting unmodifiableBetting() {
        return new Betting(Collections.unmodifiableMap(bettingMap));
    }

    public Set<Player> getPlayers() {
        return bettingMap.keySet();
    }
}
