package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Gambler extends Player {
    public Gambler(String name) {
        super(name);
    }

    @Override
    public List<Card> getOpenedCards() {
        return this.getCards();
    }
}
