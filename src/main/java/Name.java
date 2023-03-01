public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이나 빈 값이 들어갈 수 없습니다.");
        }
    }
}
