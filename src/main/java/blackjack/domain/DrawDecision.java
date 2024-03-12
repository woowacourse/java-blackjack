package blackjack.domain;

public enum DrawDecision {

    HIT,
    STAY;

    public boolean isHit() {
        return this == HIT;
    }
}
