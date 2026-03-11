package domain.model;

public enum DeckStatus {
    BURST("버스트"),
    ALIVE("생존");

    private final String name;

    DeckStatus(String name) {
        this.name = name;
    }
}
