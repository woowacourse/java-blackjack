package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_MAX_VALUE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDealerHit() {
        return this.getGameScore().isDealerHit();
    }

    @Override
    public List<Card> getOpenedCards() {
        return List.of(getCards().getFirst());
    }
}
