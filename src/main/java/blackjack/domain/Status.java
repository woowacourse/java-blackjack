package blackjack.domain;

public enum Status {

    HIT("히트"),
    STAY("스테이"),
    BURST("버스트");

    private final String name;

    Status(final String name) {
        this.name = name;
    }
}
