package blackjack.domain.participant;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name("딜러"));
    }

    public boolean isHit() {
        return this.getState()
                   .isHit();
    }
}
