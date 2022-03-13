package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordFactory {

    public static final int MAX_SCORE = 21;

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
        final Record playerRecord = Record.of(dealerScore, score);
        updateDealerRecord(playerRecord.getOppositeName());

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
