package blackjack.domain.user;

public class PlayerName {

    private final String name;

    public PlayerName(final String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(final String name) {
        boolean isAllLetter = name.chars().allMatch(Character::isLetter);
        if (isAllLetter) {
            return;
        }
        throw new IllegalArgumentException("이름은 영어/한글만 입력 가능합니다.");
    }
}
