package domain;

public class Player extends Participant {

    private static final String BAN_NAME = "딜러";
    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";

    public Player(Name name, Cards cards) {
        super(name, cards);
        validate(name);
    }

    private void validate(Name name) {
        if (name.getName().equals(BAN_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

}
