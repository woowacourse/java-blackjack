package controller;

public enum GameCommand {
    HIT,
    STAND;

    public boolean isHit() {
        return this.equals(HIT);
    }
}
