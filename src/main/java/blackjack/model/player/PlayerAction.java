package blackjack.model.player;

public enum PlayerAction {
    HIT(true),
    STAND(false);

    private final boolean asked;

    PlayerAction(final boolean asked) {
        this.asked = asked;
    }

    public static PlayerAction from(final boolean askContinuance) {
        if (askContinuance) {
            return HIT;
        }
        return STAND;
    }

    public boolean isAsked() {
        return asked;
    }

    public boolean canContinue(final Player player) {
        return player.canHit() && asked;
    }
}
