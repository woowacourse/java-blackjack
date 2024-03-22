package blackjack.domain.gamer;

public class Name {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 이름을 입력해 주세요.";

    private final String name;

    public Name(String name) {
        validateIsBlank(name);
        this.name = name;
    }

    private void validateIsBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public String name() {
        return name;
    }
}
