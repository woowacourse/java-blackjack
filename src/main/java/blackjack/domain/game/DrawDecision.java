package blackjack.domain.game;

public enum DrawDecision {

    HIT,
    STAY;

    public boolean isHit() {
        return this == HIT;
    }
}
