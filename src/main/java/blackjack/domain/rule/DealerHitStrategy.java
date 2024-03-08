package blackjack.domain.rule;

public class DealerHitStrategy implements HitStrategy {

    private static final int HIT_THRESHOLD = 17;

    @Override
    public boolean canHit(Score score) {
        return score.getValue() < HIT_THRESHOLD;
    }
}
