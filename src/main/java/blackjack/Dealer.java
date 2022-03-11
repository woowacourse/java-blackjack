package blackjack;

import java.util.List;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    public Dealer() {
        super(NAME);
    }

    public boolean canHit() {
        return this.deck.isScoreLessThan(SCORE_HIT_CRITERIA);
    }

    public int countAddedCards() {
        return this.deck.countAddedCards();
    }

    public boolean isWinning(Entry entry) {
        if (entry.isBust()) {
            return true;
        }
        if (this.isBust()) {
            return false;
        }
        return entry.getScore() < this.getScore();
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

    @Override
    public List<String> getDeckToString() {
        return List.of(this.deck.getFirstCardToString());
    }
}
