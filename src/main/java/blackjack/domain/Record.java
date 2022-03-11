package blackjack.domain;

import java.util.Arrays;

public enum Record {

    WIN("승", "패"),
    PUSH("무", "무"),
    LOSS("패", "승");

    private final String name;
    private final String oppositeName;

    Record(String name, String oppositeName) {
        this.name = name;
        this.oppositeName = oppositeName;
    }

    public String getName() {
        return name;
    }

    public Record getOppositeName() {
        return Arrays.stream(Record.values())
                .filter(record -> record.name.equals(oppositeName))
                .findFirst()
                .orElseThrow();
    }
}
