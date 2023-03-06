package domain;

public class Player extends Participant {

    private static final Name BAN_NAME = new Name("딜러");
    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";
    private static final int BLACKJACK = 21;

    private Player(Name name, CardDeck cardDeck) {
        super(name, cardDeck);
        validate(name);
    }

    public static Player of(Name name, CardDeck cardDeck) {
        return new Player(name, cardDeck);
    }

    private void validate(Name name) {
        if (name.equals(BAN_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public int getTotalScore() {
        return cardDeck.calculateScore(BLACKJACK);
    }

    public boolean isMoreCardAble() {
        return getTotalScore() < BLACKJACK;
    }

}
