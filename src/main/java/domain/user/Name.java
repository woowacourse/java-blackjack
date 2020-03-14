package domain.user;

public class Name {

    private static final String EMPTY = "";
    private static final String NAME_ERROR_MESSAGE = "빈 이름이 있습니다.";

    private String name;

    private Name(String name) {
        validate(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private void validate(String name) {
        if (EMPTY.equals(name)) {
            throw new IllegalArgumentException(NAME_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
