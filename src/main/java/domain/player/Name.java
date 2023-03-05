package domain.player;

public final class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validateBlank(name);
        validateLength(name);

        return new Name(name);
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }


    public String getName() {
        return name;
    }
}
