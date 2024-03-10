package blackjack.domain.rule;

public class PlayerHitStrategy implements HitStrategy {

    public static final int HIT_THRESHOLD = 21;

    @Override
    public boolean canHit(Score score) {
        return score.getValue() <= HIT_THRESHOLD;
    }

    @Override
    public boolean canHit(int score) {
        return score <= HIT_THRESHOLD;
    }
}
