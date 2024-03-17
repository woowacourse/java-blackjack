package domain;

import java.util.List;
import java.util.stream.IntStream;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final Names names, final List<BetAmount> betAmounts) {
        return new Players(createPlayers(names, betAmounts));
    }

    private static List<Player> createPlayers(final Names names, final List<BetAmount> betAmounts) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), betAmounts.get(i)))
                .toList();
    }

    private void validate(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > 8;
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
