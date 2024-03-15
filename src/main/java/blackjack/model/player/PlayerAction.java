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

    public boolean canNotContinue(final Player player) {
        return this.equals(STAND) || !player.canHit();
    }
}
