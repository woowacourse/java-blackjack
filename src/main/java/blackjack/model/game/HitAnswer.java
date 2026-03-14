package blackjack.model.game;

public enum HitAnswer {
    HIT,
    STAY;

    public boolean isHit() {
        return this == HIT;
    }
}
