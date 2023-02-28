package domain;

public class Name {
    private static final String INVALID_NAME_LENGTH_MESSAGE = "이름길이는 5자를 초과 할 수 없습니다.";
    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validNameLength(name);
        this.name = name;
    }

    private void validNameLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }


}
