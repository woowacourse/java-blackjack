package domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return hand.isLessThanBlackJack();
    }
}
