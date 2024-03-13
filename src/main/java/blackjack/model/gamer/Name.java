package blackjack.model.gamer;

public class Name {

    private static final String BLANK = " ";
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    private Name(String name) {
        validateNameContainBlank(name);
        validateNameLength(name);
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }

    private void validateNameContainBlank(String name) {
        if (name.contains(BLANK)) {
            throw new IllegalArgumentException("[ERROR] 이름에는 공백이 포함될 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 1~10자만 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
