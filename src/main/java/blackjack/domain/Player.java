package blackjack.domain;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isNotBust();
    }
}
