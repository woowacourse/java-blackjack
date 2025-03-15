package domain.game;

public class Player extends Participant {

    private final String name;
    private final Chip chip;

    public Player(String name, int bettingAmount) {
        this.name = name;
        this.chip = new Chip(bettingAmount);
    }

    public boolean isUnderBurstBound() {
        return !this.isOverBurstBound();
    }

    public Chip calculateBettingAmount(GameResult gameResult) {
        return chip.calculateBettingAmount(gameResult);
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return chip.getBettingAmount();
    }
}
