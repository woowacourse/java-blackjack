package domain;

public class Player {

    private static final int BUST_LIMIT = 21;
    private static final String BAN_NAME = "딜러";
    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";

    private final Name name;
    private final Cards cards;

    public Player(Name name, Cards cards) {
        validate(name);
        this.name = name;
        this.cards = cards;
    }

    private void validate(Name name) {
        if (name.getName().equals(BAN_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public int getTotalScore() {
        return cards.calculateScore(BUST_LIMIT);
    }

    public boolean isBust() {
        return getTotalScore() > BUST_LIMIT;
    }

    public boolean isMoreCardAble() {
        return getTotalScore() < BUST_LIMIT;
    }

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }

}
