package blackjack.domain;

import java.util.Arrays;

public enum Record {

    WIN("승", "패"),
    LOSS("패", "승"),
    PUSH("무", "무");

    private final String name;
    private final String opposite;

    Record(String name, String opposite) {
        this.name = name;
        this.opposite = opposite;
    }

    public String getName() {
        return name;
    }

    public Record getOpposite() {
        return Arrays.stream(Record.values())
                .filter(record -> record.name.equals(opposite))
                .findFirst()
                .orElseThrow();
    }
}
