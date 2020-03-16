package domain.user;

public class Name {
    private static final String NULL_OR_EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";
    private String name;

    Name(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_NAME_ERROR_MESSAGE);
        }
        this.name = name;
    }

    public boolean isSame(String name) {
        return this.name.equals(name);
    }

    String getName() {
        return name;
    }
}
