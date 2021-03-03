package blackjack.domain;

public class Name {
    private final String name;

    public Name(String name) {
        name = name.trim();
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("공백인 이름은 사용할 수 없습니다.");
        }
    }
}
