package domain.hitStrategy;

public class UntilBustHitStrategy implements HitStrategy {
    @Override
    public boolean canHit(int score) {
        return true;
    }
}
