package blackjack.domain;

import static blackjack.domain.Record.LOSS;
import static blackjack.domain.Record.PUSH;
import static blackjack.domain.Record.WIN;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordFactory {

    private static final int MAX_SCORE = 21;

    private final int dealerScore;
    private final Map<Record, Integer> dealerRecord;
    private final Map<String, Record> playerRecord;

    public RecordFactory(final int dealerScore, final List<Player> players) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new HashMap<>();
        this.playerRecord = init(players);
    }

    RecordFactory(final int dealerScore) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new HashMap<>();
        this.playerRecord = new HashMap<>();
    }

    private Map<String, Record> init(final List<Player> players) {
        final Map<String , Record> playerRecord = new HashMap<>();
        for (Player player : players) {
            playerRecord.put(player.getName(), compareScore(player.getScore()));
        }
        return playerRecord;
    }

    public Map<String, Record> getAllPlayerRecord() {
        return playerRecord;
    }

    Record getPlayerRecord(int score) {
        return compareScore(score);
    }

    private Record compareScore(int score) {
        final Record playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOpposite());

        return playerRecord;
    }

    private Record createRecord(int score) {
        if (dealerScore > MAX_SCORE) {
            return getRecordWhenDealerBust(score);
        }

        if (score > MAX_SCORE || score < dealerScore) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private Record getRecordWhenDealerBust(int score) {
        if (score > MAX_SCORE) {
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
