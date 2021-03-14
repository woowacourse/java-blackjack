package blackjack.dto;

import blackjack.domain.card.Score;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gametable.Outcome;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultDto {
    private final Score dealerScore;
    private final List<PlayerResult> playerResults;

    public ResultDto(final Score dealerScore, final List<PlayerResult> playerResults) {
        this.dealerScore = dealerScore;
        this.playerResults = playerResults;
    }

    public Map<String, Outcome> summarizePlayersFinalOutcome() {
        final Map<String, Outcome> results = new LinkedHashMap<>();

        for (PlayerResult player : playerResults) {
            results.put(player.getName(), player.getOutcome());
        }

        return Collections.unmodifiableMap(results);
    }

    public Map<Outcome, Integer> summarizeFinalOutcomeOfPlayers() {
        final Map<Outcome, Integer> outcomes = new EnumMap<>(Outcome.class);
        Arrays.stream(Outcome.values()).forEach(outcome -> outcomes.put(outcome, 0));

        for (PlayerResult player : playerResults) {
            outcomes.put(player.getOutcome(), outcomes.get(player.getOutcome()) + 1);
        }
        return Collections.unmodifiableMap(outcomes);
    }

    public Map<String, BigDecimal> summarizePlayersProfit() {
        final Map<String, BigDecimal> results = new LinkedHashMap<>();

        for (PlayerResult player : playerResults) {
            results.put(player.getName(), player.getReturnMoney().toBigDecimal());
        }

        return results;
    }

    public BigDecimal summarizeDealerProfit() {
        BettingMoney dealerResult = BettingMoney.ZERO;
        for (PlayerResult player : playerResults) {
            final BettingMoney playerProfit = player.getReturnMoney()
                .subtract(player.getInputMoney());

            dealerResult = dealerResult.subtract(playerProfit);
        }

        return dealerResult.toBigDecimal();
    }


}
