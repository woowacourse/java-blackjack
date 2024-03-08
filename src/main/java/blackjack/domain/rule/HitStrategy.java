package blackjack.domain.rule;

public interface HitStrategy {

    public boolean canHit(Score score);
}
