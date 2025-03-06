package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    public Dealer() {
        super();
    }

    @Override
    public List<Card> getOpenedCards() {
        return getCards().subList(0, 1);
    }
}
