package blackjack.model.game;

public enum HitAnswer {
    HIT,
    STAND;

    public boolean isHit() {
        return this == HIT;
    }
}
