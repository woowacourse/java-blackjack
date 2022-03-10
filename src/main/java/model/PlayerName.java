package model;

public class PlayerName {

    private final String value;

    public PlayerName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isSameName(String other) {
        return value.equals(other);
    }
}
