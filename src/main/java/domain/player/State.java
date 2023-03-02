package domain.player;

public enum State {
    HIT,
    STAY,
    ;

    public boolean isHit() {
        return this == HIT;
    }
}
