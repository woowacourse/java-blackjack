package blackjack.domain.player;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }
}
