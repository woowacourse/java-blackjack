package domain;

public class Player extends Participant {

    private static final Name BAN_NAME = new Name("딜러");
    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";
    private static final int BLACKJACK = 21;

    private Player(Name name, Cards cards) {
        super(name, cards);
        validate(name);
    }

    public static Player of(Name name, Cards cards) {
        return new Player(name, cards);
    }

    private void validate(Name name) {
        if (name.equals(BAN_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public int getTotalScore() {
        return cards.calculateScore(BLACKJACK);
    }

    public boolean isMoreCardAble() {
        return getTotalScore() < BLACKJACK;
    }

}
