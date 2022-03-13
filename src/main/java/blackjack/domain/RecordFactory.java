package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordFactory {

    private static final Map<Record, Record> oppositeValue;

    static {
        final Map<Record, Record> map = new HashMap<>();
        map.put(Record.WIN, Record.LOSS);
        map.put(Record.PUSH, Record.PUSH);
        map.put(Record.LOSS, Record.WIN);

        oppositeValue = map;
    }

    private final int dealerScore;
    private final Map<Record, Integer> dealerRecord;
    private final Map<String, Record> playerRecord;

    public RecordFactory(final int dealerScore, final Map<String, Integer> playerResult) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new HashMap<>();
        this.playerRecord = toRecordMap(playerResult);
    }

    private Map<String, Record> toRecordMap(final Map<String, Integer> playerResult) {
        return playerResult.keySet().stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> compareScore(playerResult.get(key)),
                        (a, b) -> b,
                        LinkedHashMap::new));
    }

    private Record compareScore(int score) {
        final Record playerRecord = Record.of(dealerScore, score);
        updateDealerRecord(oppositeValue.get(playerRecord));

        return playerRecord;
    }

    private void updateDealerRecord(Record record) {
        dealerRecord.put(record, dealerRecord.getOrDefault(record, 0) + 1);
    }

    public Map<Record, Integer> getDealerRecord() {
        return new HashMap<>(dealerRecord);
    }

    public Map<String, Record> getAllPlayerRecord() {
        return new LinkedHashMap<>(playerRecord);
    }
}
