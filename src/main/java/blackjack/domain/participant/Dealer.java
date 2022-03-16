package blackjack.domain.participant;

import blackjack.domain.Record;
import blackjack.domain.card.Card;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Participant {

    private static final int SCORE_LOWER_BOUND = 17;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean canDrawCard() {
        return getScore() < SCORE_LOWER_BOUND;
    }

    public Card openFirstCard() {
        return getCards().findFirst();
    }

    public Map<Record, Integer> calculateRecord(final List<Record> playerRecords) {
        Map<Record, Integer> map = getInitMap();
        playerRecords.stream()
                .map(playerRecord -> Record.fromOppositeName(playerRecord.getName()))
                .forEach(dealerRecord -> map.put(dealerRecord, map.get(dealerRecord) + 1));

        return map;
    }

    private Map<Record, Integer> getInitMap() {
        return Arrays.stream(Record.values())
                .collect(Collectors.toMap(
                        record -> record,
                        record -> 0,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }
}
