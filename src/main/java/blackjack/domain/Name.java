package blackjack.domain;

public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름을 정확하게 입력해주세요.");
        }
    }

    public String getName() {
        return name;
    }
}
