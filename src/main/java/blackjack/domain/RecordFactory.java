package blackjack.domain;

import static blackjack.domain.Record.LOSS;
import static blackjack.domain.Record.PUSH;
import static blackjack.domain.Record.WIN;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordFactory {

    private static final int MAX_SCORE = 21;

    private final int dealerScore;
    private final Map<Record, Integer> dealerRecord;
    private final Map<String, Record> playerRecord;

    public RecordFactory(final int dealerScore, final List<Player> players) {
        this.dealerScore = dealerScore;
        this.dealerRecord = new HashMap<>();
        this.playerRecord = toMap(players);
    }

    private Map<String, Record> toMap(final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName, player -> compareScore(player.getScore()), (a, b) -> b, LinkedHashMap::new));
    }

    Record getPlayerRecord(int score) {
        return compareScore(score);
    }

    private Record compareScore(int score) {
        final Record playerRecord = createRecord(score);
        updateDealerRecord(playerRecord.getOppositeName());

        return playerRecord;
    }

    public Map<String, Record> getAllPlayerRecord() {
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
