package blackjack.model.player;

public enum PlayerAction {
    HIT,
    STAND;

    public static PlayerAction from(final boolean actionCommand) {
        if (actionCommand) {
            return HIT;
        }
        return STAND;
    }

    public boolean canNotContinue(final Player player) {
        return this.equals(STAND) || !player.canHit();
    }
}
