package domain;

public class Dealer {

    private static final int BUST_LIMIT = 21;
    private static final int MORE_CARD_LIMIT = 16;

    private final Name name = new Name("딜러");
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public int getTotalScore() {
        return cards.calculateScore(MORE_CARD_LIMIT);
    }

    public boolean isBust() {
        return getTotalScore() > BUST_LIMIT;
    }

    public Cards getCards() {
        return cards;
    }

}
