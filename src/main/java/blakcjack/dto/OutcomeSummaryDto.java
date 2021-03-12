package blakcjack.dto;

import blakcjack.domain.outcome.Outcome;

import java.util.Map;

public class OutcomeSummaryDto {
    private final Map<Outcome, Integer> dealerOutcome;
    private final Map<String, Outcome> playersOutcome;

    private OutcomeSummaryDto(final Map<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playersOutcome) {
        this.dealerOutcome = dealerOutcome;
        this.playersOutcome = playersOutcome;
    }

    public static OutcomeSummaryDto of(final Map<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playersOutcome) {
        return new OutcomeSummaryDto(dealerOutcome, playersOutcome);
    }

    public Map<String, Outcome> getPlayersOutcome() {
        return playersOutcome;
    }

    public Map<Outcome, Integer> getDealerOutcome() {
        return dealerOutcome;
    }
}
