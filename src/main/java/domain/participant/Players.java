package domain.participant;

import domain.Money;

import java.util.*;

public class Players {

    public static final int MAX_SIZE = 5;

    private final Map<Player, Money> players;

    public Players(Map<Player, Money> players) {
        validateSize(players);
        this.players = players;
    }

    public static Players withoutBetting(Set<Player> players) {
        Map<Player, Money> zeroCostPlayers = new HashMap<>();
        for (Player player : players) {
            zeroCostPlayers.put(player, Money.zero());
        }
        return new Players(zeroCostPlayers);
    }

    private void validateSize(Map<Player, Money> players) {
        if (players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어는 최대 5명입니다.");
        }
    }

    public Money getBettingMoneyOf(Player player) {
        if (!players.containsKey(player)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        return players.get(player);
    }

    public Set<Player> get() {
        return Collections.unmodifiableSet(players.keySet());
    }
}
