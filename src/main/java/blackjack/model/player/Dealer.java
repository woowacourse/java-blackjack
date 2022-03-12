package blackjack.model.player;

import blackjack.model.card.Card;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canReceive() {
        return this.deck.isScoreLessThan(SCORE_HIT_CRITERIA);
    }

    @Override
    public void receive(Card card) {
        this.deck.add(card);
    }
}
