package domain;

public enum GameStatus {

    WIN("승", 1.0),
    TIE("무", 0.0),
    LOSE("패", -1.0),
    BLACKJACK("블랙잭", 1.5);

    private final String name;
    private final double profiteRate;

    GameStatus(String name, double profiteRate) {
        this.name = name;
        this.profiteRate = profiteRate;
    }

    public String getName() {
        return name;
    }

    public double getProfiteRate() {
        return profiteRate;
    }
}
