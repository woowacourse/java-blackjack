package domain.gamer;

public class Player extends Gamer {
    private final Betting betting;

    public Player(Nickname name, Betting betting) {
        super(name);
        this.betting = betting;
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
