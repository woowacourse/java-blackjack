package domain.hitStrategy;

public class CasinoDealerHitStrategy implements HitStrategy {
    public static final int BOUNDARY = 16;

    @Override
    public boolean canHit(int score) {
        return score <= BOUNDARY;
    }
}
