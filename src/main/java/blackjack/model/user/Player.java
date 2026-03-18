package blackjack.model.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitAvailable() {
        return !isFinished();
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
