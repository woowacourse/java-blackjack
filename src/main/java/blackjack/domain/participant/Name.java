package blackjack.domain.participant;

public class Name {

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name.strip();
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 적어도 한 글자 이상을 포함해야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
