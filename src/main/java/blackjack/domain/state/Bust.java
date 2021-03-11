package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust implements State{
    private Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
