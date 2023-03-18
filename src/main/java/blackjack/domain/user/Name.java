package blackjack.domain.user;

public class Name {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 10;

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름의 길이는 %d 이상 %d 이하로 입력해주세요.", MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH));
        }
    }

    public String getName() {
        return name;
    }
}
