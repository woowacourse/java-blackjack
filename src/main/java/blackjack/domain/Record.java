package blackjack.domain;

public enum Record {

    WIN("승"),
    LOSS("패"),
    PUSH("무");

    private final String name;

    Record(String name) {
        this.name = name;
    }
}
