package domain;

public class Gamer extends Player {
    private final String name;

    public Gamer(final String name) {
        super();
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름으로 빈 문자열이 입력되었습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
