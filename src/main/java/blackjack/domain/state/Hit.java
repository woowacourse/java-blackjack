package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Hit implements State{
    private Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
