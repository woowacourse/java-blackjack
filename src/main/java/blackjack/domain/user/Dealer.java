package blackjack.domain.user;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isHit();
    }
}
