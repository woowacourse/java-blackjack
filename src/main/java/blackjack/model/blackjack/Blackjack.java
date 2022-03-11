package blackjack.model.blackjack;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

public class Blackjack {

    private CardDispenser cardDispenser;
    private Participants participants;

    public Blackjack(CardDispenser cardDispenser, String... names) {
        this.cardDispenser = cardDispenser;
        this.participants = new Participants(cardDispenser, names);
    }

    public Records records() {
        return new Records(recordsMap());
    }

    private Map<String, Record> recordsMap() {
        return participants.records().stream()
            .collect(toMap(Record::name, record -> record));
    }
}
