package blackjack.domain.participant;

public record PlayerName(
        String value
) {
    public PlayerName {
        validateName(value);
    }

    private void validateName(final String value) {
        boolean isAllLetter = value.chars().allMatch(Character::isLetter);
        if (isAllLetter) {
            return;
        }
        throw new IllegalArgumentException("이름은 글자만 입력 가능합니다.");
    }
}
