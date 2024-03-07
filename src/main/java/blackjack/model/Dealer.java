package blackjack.model;

import java.util.List;

public class Dealer extends Player {
    private static final int HITTABLE_THRESHOLD = 16;

    public Dealer(final Cards cards) {
        super("딜러", cards);
    }

    @Override
    public boolean canHit() {
        return cards.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> openCard() {
        return List.of(cards.getFirstCard());
    }
}
