package model;

public class PlayerName {

    private final String name;

    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNameIsNotBlank(name);
    }

    private void validateNameIsNotBlank(String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("");
        }
    }

    public String get() {
        return name;
    }
}
