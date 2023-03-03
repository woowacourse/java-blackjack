package domain;

public abstract class Participant {

    private static final int BUST_LIMIT = 21;

    protected final Name name;
    protected final Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public boolean isBust() {
        return getTotalScore() > BUST_LIMIT;
    }

    public int getTotalScore() {
        return cards.calculateScore(BUST_LIMIT);
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
