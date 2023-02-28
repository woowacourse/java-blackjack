package domain.player;

public enum PlayerRole {
    NORMAL, DEALER;

    public boolean isDealer() {
        return this == DEALER;
    }
}
