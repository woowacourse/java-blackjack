package domain.participant;

public class Name {

    private static final int MINIMUM_NAME_LENGTH = 5;

    private final String name;

    public Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (isNullAndEmpty(name) || validateLength(name)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateLength(final String name) {
        return name.length() > MINIMUM_NAME_LENGTH;
    }

    private boolean isNullAndEmpty(final String name) {
        return name == null || name.isEmpty();
    }

    public String getName() {
        return this.name;
    }
}
