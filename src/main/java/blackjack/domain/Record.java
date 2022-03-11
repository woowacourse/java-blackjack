package blackjack.domain;

import java.util.Arrays;

public enum Record {

    WIN("승", "패"),
    PUSH("무", "무"),
    LOSS("패", "승");

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
