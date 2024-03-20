package domain.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    public static final int MAX_PLAYER_COUNT = 8;
    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final Map<Name, BetAmount> mapNamesToBetAmounts) {
        return new Players(createPlayers(mapNamesToBetAmounts));
    }

    private static List<Player> createPlayers(final Map<Name, BetAmount> mapNamesToBetAmounts) {
        return mapNamesToBetAmounts.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private void validate(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > MAX_PLAYER_COUNT;
    }

    public int totalProfit(final Dealer dealer) {
        int totalProfit = 0;
        for (final Player player : players) {
            totalProfit += player.profit(dealer);
        }
        return totalProfit;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
