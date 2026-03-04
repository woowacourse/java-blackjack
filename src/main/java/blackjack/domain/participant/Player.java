package blackjack.domain.participant;

public class Player extends Participant {

    private static final String BLANK_NAME_MESSAGE = "이름은 공백일 수 없습니다.";

    private boolean wantsHit = true;

    public Player(final String name) {
        super(validateName(name));
    }

    private static String validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_MESSAGE);
        }
        return name;
    }

    public void stay() {
        wantsHit = false;
    }

    @Override
    public boolean canReceiveCard() {
        return wantsHit && !isBust();
    }
}
