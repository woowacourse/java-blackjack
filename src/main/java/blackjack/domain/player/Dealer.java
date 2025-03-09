package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    public static final String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_NAME);
    }

    @Override
    public List<Card> getOpenedCards() {
        return getCards().subList(0, 1);
    }
}
