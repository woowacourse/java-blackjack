package blackjack.domain.rule;

public class Score {

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean hitAllowed(HitStrategy hitStrategy) {
        return hitStrategy.canHit(this);
    }

    public boolean isAbove(int bustCondition) {
        return bustCondition < value;
    }

    public int getValue() {
        return value;
    }
}
