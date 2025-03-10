package model;

public enum PlayerChoice {
    HIT("hit"),
    STAND("stand");

    private final String choiceName;

    PlayerChoice(String choiceName) {
        this.choiceName = choiceName;
    }
}
