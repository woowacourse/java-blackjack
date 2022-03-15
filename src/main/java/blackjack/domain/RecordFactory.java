package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class RecordFactory {

    private final int dealerScore;
    private final Map<PlayRecord, Integer> dealerRecord;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new EnumMap<>(PlayRecord.class);
    }

    public PlayRecord getPlayerRecord(int score) {
        PlayRecord playerPlayRecord = createRecord(score);
        updateDealerRecord(playerPlayRecord.getOpposite());

        return playerPlayRecord;
    }

    private PlayRecord createRecord(int score) {
        return PlayRecord.of(dealerScore, score);
    }

    private void updateDealerRecord(PlayRecord playRecord) {
        dealerRecord.put(playRecord, dealerRecord.getOrDefault(playRecord, 0) + 1);
    }

    public Map<PlayRecord, Integer> getDealerRecord() {
        return Map.copyOf(dealerRecord);
    }
}
