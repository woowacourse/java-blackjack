package blackjack.domain.participant;

public class Name {

    static final String EMPTY_NAME_EXCEPTION_MESSAGE = "[ERROR] 이름에 빈값을 입력할 수 없습니다.";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
