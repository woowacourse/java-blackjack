package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class RecordFactory {

    public static final int BUST_SCORE = 21;

    private final int dealerScore;
    private final boolean isDealerBust;
    private final Map<Record, Integer> dealerRecord;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
        this.isDealerBust = dealerScore > BUST_SCORE;
        this.dealerRecord = new HashMap<>();
    }

    public Record getPlayerRecord(int score) {
        Record playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOpposite());

        return playerRecord;
    }

    private Record createRecord(int score) {
        return Record.of(isDealerBust, dealerScore, score);
    }

    private void updateDealerRecord(Record record) {
        dealerRecord.put(record, dealerRecord.getOrDefault(record, 0) + 1);
    }

    public Map<Record, Integer> getDealerRecord() {
        return dealerRecord;
    }
}
