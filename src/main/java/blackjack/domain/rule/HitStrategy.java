package blackjack.domain.rule;

public interface HitStrategy {

    public boolean canHit(Score score);
    // TODO: 제거

    public boolean canHit(int score);
}
