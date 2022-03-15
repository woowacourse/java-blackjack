package blackjack.model.player;

public class Dealer extends Participant {
    private static final int SCORE_HIT_CRITERIA = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean isImpossibleHit() {
        return cards.sumScore() > SCORE_HIT_CRITERIA;
    }
}
