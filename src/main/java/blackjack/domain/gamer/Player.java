package blackjack.domain.gamer;

public class Player extends Gamer {

    private static final int DRAWABLE_NUMBER = 21;

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }
}
