package domain.participant;

public class Name {
    private static final int NAME_LIMIT_LENGTH = 20;

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(validateName(name));
    }

    public String getName() {
        return name;
    }

    private static String validateName(String name) {
        validateNullOrEmpty(name);
        validateLength(name);
        return name.trim();
    }

    private static void validateLength(String name) {
        if (name.trim().length() > NAME_LIMIT_LENGTH) {
            throw new IllegalArgumentException("이름은 20자 이하로 입력해주세요.");
        }
    }

    private static void validateNullOrEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 확인해주세요.");
        }
    }
}
