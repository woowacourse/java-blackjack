package blackjack.domain;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isHit();
    }
}
