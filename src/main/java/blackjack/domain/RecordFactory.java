package blackjack.domain;

import static blackjack.domain.Record.LOSS;
import static blackjack.domain.Record.PUSH;
import static blackjack.domain.Record.WIN;

import java.util.HashMap;
import java.util.Map;

public class RecordFactory {

    private final int dealerScore;
    private final boolean isDealerBust;
    private final Map<Record, Integer> dealerRecord;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
        this.isDealerBust = dealerScore > 21;
        this.dealerRecord = new HashMap<>();
    }

    public Record getPlayerRecord(int score) {
        final Record playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOpposite());

        return playerRecord;
    }

    private Record createRecord(int score) {
        if (isDealerBust) {
            return getRecordWhenDealerBust(score);
        }

        if (score > 21 || score < dealerScore) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private Record getRecordWhenDealerBust(int score) {
        if (score > 21) {
            return LOSS;
        }

        return WIN;
    }

    private void updateDealerRecord(Record record) {
        dealerRecord.put(record, dealerRecord.getOrDefault(record, 0) + 1);
    }

    public Map<Record, Integer> getDealerRecord() {
        return dealerRecord;
    }
}
