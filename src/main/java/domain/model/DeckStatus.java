package domain.model;

public enum DeckStatus {
    BURST("버스트"),
    ALIVE("생존"),
    BLACKJACK("블랙잭");

    private final String name;

    DeckStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
