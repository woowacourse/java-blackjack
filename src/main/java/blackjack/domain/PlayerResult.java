package blackjack.domain;

public class PlayerResult {

    private final String name;
    private final int benefit;

    public PlayerResult(String name, int benefit) {
        this.name = name;
        this.benefit = benefit;
    }

    public String getName() {
        return name;
    }

    public int getBenefit() {
        return benefit;
    }
}
