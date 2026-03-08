package blackjack.model;

public class Dealer extends Participant {

    @Override
    public boolean canReceive() {
        return score.isDealerHitScore();
    }
}
