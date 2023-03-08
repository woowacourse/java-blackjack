package blackjackgame.domain.player;

import java.util.List;

import blackjackgame.domain.card.Card;

public class Dealer extends Player {
    private static final int DEALER_HIT_STANDARD = 16;

    public Dealer(List<Card> cards) {
        super(cards);
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_STANDARD;
    }

    public String getName() {
        return "딜러";
    }
}
