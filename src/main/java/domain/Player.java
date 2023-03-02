package domain;

public class Player {

    private static final int BUST_LIMIT = 21;

    private final Name name;
    private final Cards cards;

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
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

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }

}
