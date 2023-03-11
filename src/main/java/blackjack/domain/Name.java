package blackjack.domain;

public class Name {
    private final String name;

    public Name(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("공백은 이름이 될 수 없습니다.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
