package blackjack.domain.player;

public enum DrawDecision {

    HIT,
    STAY,
    ;

    DrawDecision() {
    }

    public boolean isHit() {
        return this == HIT;
    }
}
