package domain.gamer;

public class Player extends Gamer {

    public Player(Nickname name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return !isBurst();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
