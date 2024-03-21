package blackjack.player;

public class Name {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateNonBlankName(name);
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "이름은 " + MIN_NAME_LENGTH + "글자 이상 " + MAX_NAME_LENGTH + "글자 이하여야 합니다."
            );
        }
    }

    private void validateNonBlankName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    String getName() {
        return name;
    }
}
