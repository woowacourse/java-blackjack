package blackjack.domain;

public class Player extends Participant {
    private static final String RESERVED_NAME = "딜러";

    public Player(String name) {
        super(new Name(name));
        validateReservedName(name);
    }

    private void validateReservedName(String name) {
        if (RESERVED_NAME.equals(name)) {
            throw new IllegalArgumentException("이름은 딜러일 수 없습니다.");
        }
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isNotBust();
    }
}
