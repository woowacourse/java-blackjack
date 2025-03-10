package domain.game;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public boolean isUnderBurstBound() {
        return !this.isOverBurstBound();
    }

    public String getName() {
        return name;
    }
}
