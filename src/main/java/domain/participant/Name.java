package domain.participant;

public class Name {
    public static final int NAME_LENGTH_THRESHOLD = 5;
    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() > NAME_LENGTH_THRESHOLD) {
            throw new IllegalArgumentException();
        }
    }

    public String name() {
        return name;
    }
}
