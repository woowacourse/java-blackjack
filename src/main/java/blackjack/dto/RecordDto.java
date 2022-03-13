package blackjack.dto;

import blackjack.domain.Record;
import java.util.Map;

public class RecordDto {

    private final Map<Record, Integer> dealerRecord;
    private final Map<String, Record> playerRecord;

    private RecordDto(final Map<Record, Integer> dealerRecord,
                      final Map<String, Record> playerRecord) {
        this.dealerRecord = dealerRecord;
        this.playerRecord = playerRecord;
    }

    public static RecordDto of(Map<Record, Integer> dealerRecord, Map<String, Record> playerRecord) {
        return new RecordDto(dealerRecord, playerRecord);
    }

    public Map<Record, Integer> getDealerRecord() {
        return dealerRecord;
    }

    public Map<String, Record> getPlayerRecord() {
        return playerRecord;
    }
}
