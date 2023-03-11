package domain.game;

public enum Winning {
    BLACKJACK("블랙잭"),
    LOSE("패"),
    PUSH("무"),
    WIN("승");

    private final String name;

    Winning(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
