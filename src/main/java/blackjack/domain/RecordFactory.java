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
        PlayRecord playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOpposite());

        return playerRecord;
    }

    private PlayRecord createRecord(int score) {
        return PlayRecord.of(dealerScore, score);
    }

    private void updateDealerRecord(PlayRecord playRecord) {
        dealerRecord.merge(playRecord, 1, Integer::sum);
    }

    public Map<PlayRecord, Integer> getDealerRecord() {
        return Map.copyOf(dealerRecord);
    }
}
