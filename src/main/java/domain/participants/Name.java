package domain.participants;

public class Name {

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 한 글자 이상이어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
