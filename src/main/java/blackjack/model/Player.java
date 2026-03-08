package blackjack.model;

public class Player extends Participant {
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean canReceive() {
        return score.isPlayerHitScore();
    }
}
