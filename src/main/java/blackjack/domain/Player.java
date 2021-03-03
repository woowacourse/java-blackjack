package blackjack.domain;

public class Player extends AbstractPlayer {
    public Player() {
        super();
    }

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isCanDraw() {
        return getValue() <= BLACKJACK;
    }
}
