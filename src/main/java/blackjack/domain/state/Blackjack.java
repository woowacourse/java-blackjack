package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack implements State{
    private Cards cards;

    public Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
