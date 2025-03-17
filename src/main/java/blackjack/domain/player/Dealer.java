package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_NAME, 0);
    }

    @Override
    public List<Card> getOpenedCards() {
        Card card = this.getHand().getCards().getTopCard();
        if (card == null) {
            return List.of();
        }
        return List.of(card);
    }
}
