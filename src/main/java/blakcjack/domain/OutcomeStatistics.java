package blakcjack.domain;

import java.util.Map;
import java.util.Objects;

public class OutcomeStatistics {
    private final Map<Outcome, Integer> dealerOutcome;
    private final Map<String, Outcome> playersOutcome;

    public OutcomeStatistics(final Map<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playersOutcome) {
        this.dealerOutcome = dealerOutcome;
        this.playersOutcome = playersOutcome;
    }

    public Map<String, Outcome> getPlayersOutcome() {
        return playersOutcome;
    }

    public Map<Outcome, Integer> getDealerOutcome() {
        return dealerOutcome;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OutcomeStatistics that = (OutcomeStatistics) o;
        return Objects.equals(dealerOutcome, that.dealerOutcome) && Objects.equals(playersOutcome, that.playersOutcome);
    }
}
