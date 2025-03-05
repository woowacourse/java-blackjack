package domain;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    public Dealer(final CardHand hand) {
        super(hand);
    }

    public boolean isPickCard() {
        return calculateAllScore() <= 16;
    }

}
