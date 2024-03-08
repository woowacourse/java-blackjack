package domain.gamer;

public class Player extends Gamer {

    public static final String DEALER_NAME = "딜러";
    public static final String NOT_ALLOWED_NAME = String.format("%s는 사용할 수 없는 이름입니다.", DEALER_NAME);


    public Player(final Name name) {
        super(name);
        validateInvalidName(name);
    }

    private void validateInvalidName(final Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(NOT_ALLOWED_NAME);
        }
    }

    @Override
    public boolean isStay() {
        return hand.isOverBlackJack();
    }
}
