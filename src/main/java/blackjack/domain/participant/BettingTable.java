package blackjack.domain.participant;

import static java.util.Collections.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.PlayRecord;

class BettingTable {

    private final List<Betting> bettings;

    BettingTable(List<Betting> bettings) {
        this.bettings = List.copyOf(bettings);
    }

    Map<Name, Long> getRevenues(Name dealerName, Map<Name, PlayRecord> recordMap) {
        Map<Name, PlayRecord> copyOfRecordMap = Map.copyOf(recordMap);
        Map<Name, Long> result = new LinkedHashMap<>();

        result.put(dealerName, dealerRevenue(copyOfRecordMap));
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
