package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay implements State{
    private Cards cards;

    public Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
