package domain;

public class Player extends Gamer {
    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return !this.isBust();
    }
}
