package domain.player;

public enum HitState {
    INIT,
    HIT,
    STAY,
    ;

    public static HitState hitWhenBooleanIsTrue(final boolean isHit) {
        if (isHit) {
            return HIT;
        }
        return STAY;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isStay() {
        return this == STAY;
    }
}
