package blackjack.domain.participant;

public class Name {
    private final String name;

    public Name(String name) {
        validateEmptyName(name);
        validateNameLength(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("길이는 100자를 초과할 수 없습니다.");
        }
    }

    public String getValue() {
        return name;
    }
}
