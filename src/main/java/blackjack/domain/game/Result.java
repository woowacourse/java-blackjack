package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private final Map<Player, PlayerState> resultMap;

    private Result(Map<Player, PlayerState> resultMap) {
        this.resultMap = resultMap;
    }

    public static Result of(List<Player> players, Dealer dealer) {
        Map<Player, PlayerState> currentResultMap = new LinkedHashMap<>();
        for (Player player : players) {
            currentResultMap.put(player, PlayerState.of(player, dealer));
        }
        return new Result(currentResultMap);
    }

    public Map<PlayerState, Long> countDealerResults() {
        return resultMap.values().stream()
                .map(this::replaceToDealerState)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    private PlayerState replaceToDealerState(PlayerState playerState) {
        if (playerState == PlayerState.BLACKJACK || playerState == PlayerState.WIN) {
            return PlayerState.LOSE;
        }
        if (playerState == PlayerState.LOSE) {
            return PlayerState.WIN;
        }
        return playerState;
    }

    public PlayerState getPlayerState(Player player) {
        return resultMap.get(player);
    }

    public double getMultiple(Player player) {
        return getPlayerState(player).getMultiple();
    }
}
