package blackjack;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isBust() {
        return this.deck.isBust();
    }

    @Override
    public void hit(TrumpCard card) {
        if (canHit()) {
            this.deck.add(card);
        }
    }

    public boolean canHit() {
        return this.deck.isScoreLessThan(SCORE_HIT_CRITERIA);
    }

    public String getFirstDeckToString() {
        return this.deck.getFirstCardToString();
    }

    public int countAddedCards() {
        return this.deck.countAddedCards();
    }
}
