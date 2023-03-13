package blackjack.domain.user;

public final class Player extends User {

    public Player(final String name) {
        super(name);
        validationName();
    }

    private void validationName() {
        validateBlank(getName());
        validateLength(getName());
        validateReservedWord(getName());
    }

    private static void validateReservedWord(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("사용자 이름은 딜러일 수 없습니다.");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("사용자 이름은 10자 이하여야 합니다.");
        }
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 한글자 이상이여야 합니다.");
        }
    }


}
