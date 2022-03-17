package blackjack.domain;

import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final Map<PlayerOutcome, List<Player>> result;

    public GameResult(Map<PlayerOutcome, List<Player>> result) {
        this.result = result;
    }

    public Map<Participant, Double> getBettingResult() {
        return result.entrySet().stream()
                .map(entry -> entry.getValue()
                        .stream()
                        .collect(Collectors.toMap(player -> player, player -> getBettingMoney(player, entry.getKey())))
                ).flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public double getBettingMoney(Player player, PlayerOutcome playerOutcome) {
        if (playerOutcome == PlayerOutcome.WIN) {
            return player.getBettingMoney();
        }
        if (playerOutcome == PlayerOutcome.LOSE) {
            return -player.getBettingMoney();
        }
        if (playerOutcome == PlayerOutcome.BLACKJACK_WIN) {
            return player.getBettingMoney() * 1.5;
        }
        return 0;
    }

    public double getDealerMoney(Player player, PlayerOutcome outcome) {
        return -getBettingMoney(player, outcome);
    }

    public double getDealerTotalMoney() {
        return -getBettingResult().values().stream()
                .mapToDouble(money -> money)
                .sum();
    }
}
