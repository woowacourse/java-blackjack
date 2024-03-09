package blackjack.domain.participant;

public enum PlayerAction {
    STAND,
    HIT;

    public static PlayerAction getAction(boolean dosePlayerWantHit) {
        if (dosePlayerWantHit) {
            return HIT;
        }
        return STAND;
    }
}
