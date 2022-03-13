package blackjack.domain;

import static blackjack.domain.PlayStatus.*;

import java.util.HashMap;
import java.util.Map;

public class RecordFactory {

    private final int dealerScore;
    private final Map<Record, Integer> dealerRecord;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new HashMap<>();
    }

    public Record getPlayerRecord(int score) {
        Record playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOpposite());

        return playerRecord;
    }

    private Record createRecord(int score) {
        return Record.of(isDealerBust(), dealerScore, score);
    }

    private boolean isDealerBust() {
        return dealerScore > BUST_SCORE;
    }

    private void updateDealerRecord(Record record) {
        dealerRecord.put(record, dealerRecord.getOrDefault(record, 0) + 1);
    }

    public Map<Record, Integer> getDealerRecord() {
        return Map.copyOf(dealerRecord);
    }
}
