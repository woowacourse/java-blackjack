package domain.gamer;

public class Player extends Gamer {

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean isStay() {
        return hand.isOverBlackJack();
    }
}
