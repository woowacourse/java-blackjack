package blackjack.dto;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.user.Player;

public class MatchRecordDto {

    private Map<String, String> playerRecords;
    private String dealerRecord;

    private MatchRecordDto(Map<String, String> playerRecords, String dealerRecord) {
        this.playerRecords = playerRecords;
        this.dealerRecord = dealerRecord;
    }

    public static MatchRecordDto fromRecords(Map<Player, blackjack.domain.MatchRecord> playerMatchRecords) {
        Map<String, String> playerRecords = playerMatchRecords.entrySet()
            .stream()
            .collect(Collectors.toMap(
                entry -> entry.getKey().getName(),
                entry -> entry.getValue().getName()));
        String dealerRecord = joinToString(addUpDealerRecord(playerMatchRecords));
        return new MatchRecordDto(playerRecords, dealerRecord);
    }

    private static Map<blackjack.domain.MatchRecord, Long> addUpDealerRecord(Map<Player, blackjack.domain.MatchRecord> playerMatchRecords) {
        return playerMatchRecords.values().stream()
            .map(blackjack.domain.MatchRecord::reverseResult)
            .collect(Collectors.groupingBy(
                Function.identity(),
                TreeMap::new,
                Collectors.counting()
            ));
    }

    private static String joinToString(Map<blackjack.domain.MatchRecord, Long> dealerRecordCounts) {
        return dealerRecordCounts.entrySet().stream()
            .map(entry -> entry.getValue() + entry.getKey().getName())
            .collect(Collectors.joining(" "));
    }

    public Map<String, String> getPlayerRecords() {
        return playerRecords;
    }

    public String getDealerRecord() {
        return dealerRecord;
    }
}
