package blackjack.model.player;

public enum isHit {
    HIT,
    STAND;

    public static isHit from(final boolean askContinuance) {
        if (askContinuance) {
            return HIT;
        }
        return STAND;
    }

    public boolean isAsked() {
        return this.equals(HIT);
    }

    public boolean canContinue(final Player player) {
        return player.canHit() && this.equals(HIT);
    }
}
