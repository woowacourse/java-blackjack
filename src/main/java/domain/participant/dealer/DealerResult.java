package domain.participant.dealer;

import java.util.Map;

import domain.BlackjackResultStatus;
import domain.participant.Name;

public class DealerResult {

    private final Dealer dealer;
    private final Map<BlackjackResultStatus, Integer> counts;

    public DealerResult(final Dealer dealer, final Map<BlackjackResultStatus, Integer> counts) {
        this.dealer = dealer;
        this.counts = counts;
    }

    public boolean contains(final BlackjackResultStatus resultStatus) {
        return counts.containsKey(resultStatus);
    }

    public void put(final BlackjackResultStatus resultStatus) {
        counts.put(resultStatus, counts.getOrDefault(resultStatus, 0) + 1);
    }

    public int countOf(final BlackjackResultStatus resultStatus) {
        return counts.get(resultStatus);
    }

    public Name getDealerName() {
        return dealer.name();
    }
}
