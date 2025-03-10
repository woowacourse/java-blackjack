package domain.game;

public class Player extends Participant {

    private final String name;
    private int bettingAmount;

    public Player(String name, int bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public boolean isUnderBurstBound() {
        return !this.isOverBurstBound();
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
