package domain.participant;

public enum State {
    HIT,
    STAY,
    ;

    public boolean isHit() {
        return this == HIT;
    }
}
