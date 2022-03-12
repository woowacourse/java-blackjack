package blackjack.model.player;

import blackjack.model.card.Card;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    private final String name;

    public Dealer() {
        super();
        this.name = NAME;
    }

    @Override
    public boolean isImpossibleHit() {
        return this.cards.isScoreLessThan(SCORE_HIT_CRITERIA);
    }

    @Override
    public void receive(Card card) {
        this.cards.add(card);
    }
}
