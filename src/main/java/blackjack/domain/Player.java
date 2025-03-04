package blackjack.domain;

public class Player {

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        boolean isAllLowerCase = name.chars().allMatch(Character::isLowerCase);
        if (isAllLowerCase) {
            return;
        }
        throw new IllegalArgumentException("이름은 알파벳 소문자만 입력 가능합니다.");
    }
}
