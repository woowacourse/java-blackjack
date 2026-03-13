package domain.participant;

public record Name(String name) {
    public Name {
        validate(name);
    }

    private void validate(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 비어 있거나 공백만 있을 수 없습니다.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어 있거나 공백만 있을 수 없습니다.");
        }
    }
}
