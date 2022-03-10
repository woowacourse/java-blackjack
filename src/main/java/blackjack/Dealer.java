package blackjack;

public class Dealer extends Player {
    private static final int SCORE_HIT_CRITERIA = 17;

    protected Dealer(String name, Deck deck) {
        super(name, deck);
    }

    public static class Builder extends Player.Builder {
        private static final String NAME = "딜러";

        public Builder() {
            super(NAME);
        }

        @Override
        public Dealer build() {
            if (deck == null) {
                throw new RuntimeException(ERROR_DECK_IS_NULL);
            }
            return new Dealer(this.name, this.deck);
        }
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

    private boolean canHit() {
        return this.deck.isScoreLessThan(SCORE_HIT_CRITERIA);
    }
}
