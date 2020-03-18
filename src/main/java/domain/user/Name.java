package domain.user;

public class Name {

    private static final String EMPTY = "";

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (EMPTY.equals(name)) {
            throw new IllegalArgumentException("빈 이름이 있습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
