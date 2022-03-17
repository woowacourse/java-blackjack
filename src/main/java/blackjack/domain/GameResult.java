package blackjack.domain;

import blackjack.domain.entry.Dealer;
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

    public BettingResult calculateBettingResult(Dealer dealer) {
        Map<Participant, Integer> bettingResult = getPlayerBettingResult();
        bettingResult.put(dealer, getDealerTotalMoney());

        return new BettingResult(bettingResult);
    }

    private Map<Participant, Integer> getPlayerBettingResult() {
        return result.entrySet().stream()
                .map(entry -> entry.getValue()
                        .stream()
                        .collect(Collectors.toMap(player -> player, player -> getBettingMoney(player, entry.getKey())))
                ).flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int getBettingMoney(Player player, PlayerOutcome playerOutcome) {
        if (playerOutcome == PlayerOutcome.WIN) {
            return player.getBettingMoney();
        }
        if (playerOutcome == PlayerOutcome.LOSE) {
            return -player.getBettingMoney();
        }
        if (playerOutcome == PlayerOutcome.BLACKJACK_WIN) {
            return (int)(player.getBettingMoney() * 1.5);
        }
        return 0;
    }

    public double getDealerMoney(Player player, PlayerOutcome outcome) {
        return -getBettingMoney(player, outcome);
    }

    public int getDealerTotalMoney() {
        return -getPlayerBettingResult().values().stream()
                .mapToInt(money -> money)
                .sum();
    }
}
