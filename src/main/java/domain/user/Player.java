package domain.user;

public class Player extends User {

    private static final int NAME_MAX_LENGTH = 5;
    private static final int BLACK_JACK_SCORE = 21;
    private static final String DEALER_NAME = "딜러";

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateBlank(name);
        validateLength(name);
        validateNotProperName(name);
    }

    private void validateNotProperName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("딜러라는 이름은 사용할 수 없습니다.");
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("플레이어의 이름은 %d보다 길 수 없습니다.", NAME_MAX_LENGTH));
        }
    }

    public boolean isRightName(final String name) {
        return name.equals(this.name);
    }

    @Override
    public boolean isHittable() {
        return cards.isUnder(BLACK_JACK_SCORE);
    }

    public String getName() {
        return name;
    }
}
