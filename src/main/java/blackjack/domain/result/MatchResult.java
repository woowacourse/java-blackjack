package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import blackjack.domain.participant.Player;

public class MatchResult {

    private final Map<String, Integer> playerOutcomes;
    private final int dealerOutcome;

    public MatchResult(final Map<Player, MatchStatus> resultOfPlayers) {
        this.playerOutcomes = calculatePlayerOutcomes(resultOfPlayers);
        this.dealerOutcome = calculateDealerOutcome(playerOutcomes);
    }

    private Map<String, Integer> calculatePlayerOutcomes(final Map<Player, MatchStatus> resultOfPlayers) {
        final Map<String, Integer> playerOutcomes = new LinkedHashMap<>();
        for (final Entry<Player, MatchStatus> entry : resultOfPlayers.entrySet()) {
            appendPlayerOutcome(playerOutcomes, entry.getKey(), entry.getValue());
        }
        return playerOutcomes;
    }

    private void appendPlayerOutcome(final Map<String, Integer> playerOutcomes,
                                     final Player player,
                                     final MatchStatus matchStatus) {
        final String playerName = player.getName();
        final int outCome = matchStatus.multiplyRate(player.getBettingAmount());
        playerOutcomes.put(playerName, outCome);
    }

    private int calculateDealerOutcome(final Map<String, Integer> playerOutcomes) {
        final int playerOutcomeSum = playerOutcomes.values().stream()
                .mapToInt(playerOutcome -> playerOutcome)
                .sum();
        return changeSignOfOutcome(playerOutcomeSum);
    }

    private int changeSignOfOutcome(final int number) {
        return -number;
    }

    public Map<String, Integer> getPlayerOutcomes() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(playerOutcomes));
    }

    public int getDealerOutcome() {
        return dealerOutcome;
    }
}
