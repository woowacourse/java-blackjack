package domain.player.participant;

public enum State {
    HIT,
    STAY,
    ;

    public boolean isHit() {
        return this == HIT;
    }
}
