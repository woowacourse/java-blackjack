package domain;

public class Player extends Gamer {

    public Player(final Name name) {
        super(name);
    }

    @Override
    boolean isStay() {
        return hand.isOverBlackJack();
    }
}
