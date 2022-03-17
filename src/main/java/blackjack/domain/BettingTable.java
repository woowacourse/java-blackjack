package blackjack.domain;

import static java.util.Collections.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participant.Name;

public class BettingTable {

    private final List<Betting> bettings;

    public BettingTable(List<Betting> bettings) {
        this.bettings = List.copyOf(bettings);
    }

    public Map<Name, Long> getRevenues(Map<Name, PlayRecord> recordMap) {
        Map<Name, PlayRecord> copyOfRecordMap = Map.copyOf(recordMap);
        Map<Name, Long> result = new LinkedHashMap<>();

        result.put(Name.of("딜러"), dealerRevenue(copyOfRecordMap));
        for (Betting betting : bettings) {
            result = betting.addRecord(result, copyOfRecordMap);
        }
        return unmodifiableMap(result);
    }

    private long dealerRevenue(Map<Name, PlayRecord> recordMap) {
        return bettings.stream()
            .mapToLong(betting -> -betting.revenue(recordMap))
            .sum();
    }
}
