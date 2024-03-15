package blackjack.model.player;

public enum PlayerAction {
    HIT,
    STAND;

    public static PlayerAction from(final boolean askContinuance) {
        if (askContinuance) {
            return HIT;
        }
        return STAND;
    }

    public boolean isHit() {
        return this.equals(HIT);
    }

    public boolean canContinue(final Player player) {
        return player.canHit() && this.equals(HIT);
    }
}
