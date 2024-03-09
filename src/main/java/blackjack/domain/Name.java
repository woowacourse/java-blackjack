package blackjack.domain;

public class Name {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 15;
    private static final String ELEMENT_NAME_RULE = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$";

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        validateNameRule(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("게임 참여자의 이름은 " + MIN_NAME_LENGTH + "이상 "
                    + MAX_NAME_LENGTH + "이하의 길이로 구성되어야 합니다.");
        }
    }

    private void validateNameRule(String name) {
        if (name.matches(ELEMENT_NAME_RULE)) {
            return;
        }
        throw new IllegalArgumentException("게임 요소의 이름은 영숫자 또는 한글로 구성되어야 합니다.");
    }

    public String getName() {
        return name;
    }
}
