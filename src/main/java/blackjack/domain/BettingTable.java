package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participant.Name;

public class BettingTable {

    private final List<Betting> bettings;

    public BettingTable(List<Betting> bettings) {
        this.bettings = List.copyOf(bettings);
    }

    private long dealerRevenue(Map<Name, PlayRecord> recordMap) {
        return bettings.stream()
            .mapToLong(betting -> -betting.revenue(recordMap))
            .sum();
    }

    public Map<Name, Long> getRevenues(Map<Name, PlayRecord> recordMap) {
        Map<Name, Long> result = new LinkedHashMap<>();
        result.put(Name.of("딜러"), dealerRevenue(recordMap));
        for (Betting betting : bettings) {
            result.put(betting.getName(), betting.revenue(recordMap));
        }
        return result;
    }
}
