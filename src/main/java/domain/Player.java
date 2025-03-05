package domain;

public class Player extends Participant {
    public Player() {
        super();
    }

    public Player(final CardHand hand) {
        super(hand);
    }

    @Override
    public boolean isPickCard() {
        return calculateAllScore() <= 21;
    }
}
