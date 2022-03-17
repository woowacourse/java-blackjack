package blackjack.domain;

import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final Map<PlayerOutcome, List<Player>> result;

    public GameResult(Map<PlayerOutcome, List<Player>> result) {
        this.result = result;
    }

    public Map<Participant, Integer> getBettingResult() {
        result.forEach(((playerOutcome, players) -> {
            players.stream()
                    .map(player -> getBettingMoney(player, playerOutcome))
                    .collect(Collectors.toList());
        }));
        return null;
    }

    public int getBettingMoney(Player player, PlayerOutcome playerOutcome) {
        if (playerOutcome == PlayerOutcome.WIN) {
            return player.getBettingMoney();
        }
        return 0;
    }
}
