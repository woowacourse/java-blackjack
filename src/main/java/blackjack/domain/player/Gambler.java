package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Gambler extends Player {

    public Gambler(final String name, final int batMoney) {
        super(name, batMoney);
    }

    @Override
    public List<Card> getOpenedCards() {
        return this.getHand().getCards().getCards();
    }
}
