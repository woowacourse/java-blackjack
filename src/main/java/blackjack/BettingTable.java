package blackjack;

import java.util.List;
import java.util.Map;

import blackjack.domain.PlayRecord;
import blackjack.domain.participant.Name;

public class BettingTable {

    private final List<Betting> bettings;

    public BettingTable(List<Betting> bettings) {
        this.bettings = List.copyOf(bettings);
    }

    public long dealerRevenue(Map<Name, PlayRecord> recordMap) {
        return bettings.stream()
            .mapToLong(betting -> -betting.revenue(recordMap))
            .sum();
    }
}
