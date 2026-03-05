package domain;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name) {
        this.name = name;
        this.cards = new Cards();
    }
}
