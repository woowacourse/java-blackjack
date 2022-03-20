package blackJack.domain.participant;

public class Dealer extends Participant {

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return this.getScore().isDealerCanHit();
    }
}
