package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class RecordFactory {

    private final int dealerScore;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    private PlayRecord getRecord(int score) {
        return PlayRecord.of(dealerScore, score);
    }

    public Map<Name, PlayRecord> getPlayerRecords(List<Player> players) {
        return players.stream()
            .collect(toUnmodifiableMap(Player::getName,
                player -> getRecord(player.getScore())));
    }
}
