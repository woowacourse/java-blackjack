package domain.player;

public final class Name {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 15;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR]: 이름은 1자이상 15자이하로 입력해주세요.");
        }
    }

    public String getName() {
        return name;
    }
}
