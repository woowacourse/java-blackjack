package blackjack.domain.game;

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
