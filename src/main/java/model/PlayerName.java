package model;

public class PlayerName {

    private final String name;

    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNameIsNotBlank(name);
        validateNameIsNotAllowed(name);
    }

    private void validateNameIsNotBlank(String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("");
        }
    }

    private void validateNameIsNotAllowed(String name) {
        if(name.equals("딜러")) {
            throw new IllegalArgumentException("");
        }
    }

    public String get() {
        return name;
    }
}
