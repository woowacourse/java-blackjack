package blackjack.domain;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    private void validateName(final String name) {
        boolean isAllLowerCase = name.chars().allMatch(Character::isLowerCase);
        if (isAllLowerCase) {
            return;
        }
        throw new IllegalArgumentException("이름은 알파벳 소문자만 입력 가능합니다.");
    }

    public String getName() {
        return name;
    }
}
