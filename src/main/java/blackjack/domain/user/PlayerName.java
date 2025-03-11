package blackjack.domain.user;

public class PlayerName {

    private final String name;

    public PlayerName(final String name) {
        validateNameSize(name);
        validateCharacter(name);

        this.name = name;
    }

    private void validateNameSize(final String name) {
        if(name.length() <= 0) {
            throw new IllegalArgumentException("이름은 한 글자 이상 입력해야 합니다.");
        }
    }

    private void validateCharacter(final String name) {
        boolean isNotAllLetter = name.chars().anyMatch(ch -> !Character.isLetter(ch));;
        if (isNotAllLetter) {
            throw new IllegalArgumentException("이름은 영어/한글만 입력 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
