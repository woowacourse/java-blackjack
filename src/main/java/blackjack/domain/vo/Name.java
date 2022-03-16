package blackjack.domain.vo;

public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateName(name);

        return new Name(name);
    }

    private static void validateName(String name) {
        validateEmpty(name);
        validateSize(name);
    }

    private static void validateSize(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("이름이 공백일 수 없습니다.");
        }
    }

    private static void validateEmpty(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백을 포함할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
