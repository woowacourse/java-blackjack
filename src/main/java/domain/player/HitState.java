package domain.player;

public enum HitState {
    INIT,
    HIT,
    STAY,
    ;

    public boolean isHit() {
        return this == HIT;
    }

    public boolean isStay() {
        return this == STAY;
    }
}
