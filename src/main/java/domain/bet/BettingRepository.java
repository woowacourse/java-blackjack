package domain.bet;

import domain.player.Player;
import domain.player.Players;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingRepository {
    private final Map<Player, Integer> value;

    private BettingRepository(final Map<Player, Integer> value) {
        this.value = value;
    }

    public static BettingRepository fromPlayers(final Players players) {
        return new BettingRepository(players.stream().collect(Collectors.toMap(
                player -> player,
                amount -> 0
        )));
    }
}
