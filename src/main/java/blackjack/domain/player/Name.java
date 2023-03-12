package blackjack.domain.player;

public class Name {
    private static final String NAME_BLANK_ERROR_MESSAGE = "공백으로만 이루어진 이름은 사용할 수 없습니다.";
    
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(NAME_BLANK_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
