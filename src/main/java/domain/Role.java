package domain;

public enum Role {
    DEALER,
    PLAYER;

    public boolean isPlayer() {
        return this == PLAYER;
    }
}
