package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {

    private static final int PLAYERS_VALID_SIZE = 7;
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.NEED_PLAYER_MEMBERS.getMessage());
        }

        if (players.size() > PLAYERS_VALID_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_PLAYER_MEMBERS.getMessage());
        }
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public void adjustBalance(Dealer dealer) {
        for (Player player : players) {
            GameResultType gameResultType = GameResultTypePlayerJudgement.find(player, dealer);

            player.adjustBalance(gameResultType);
        }
    }

    public int getTotalRevenue() {
        return getRevenueMap().values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Player, Integer> getRevenueMap() {
        return players.stream()
                .collect(Collectors.toMap(Function.identity(), Player::getRevenue));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
