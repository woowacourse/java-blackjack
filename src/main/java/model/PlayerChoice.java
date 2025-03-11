package model;

public enum PlayerChoice {
    HIT("hit"),
    STAND("stand");

    private final String choiceName;

    PlayerChoice(String choiceName) {
        this.choiceName = choiceName;
    }

    public String getChoiceName() {
        return choiceName;
    }
}
