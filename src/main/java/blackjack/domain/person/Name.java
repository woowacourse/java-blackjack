package blackjack.domain.person;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateBlankName(name);
        validateNameLength(name);
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
