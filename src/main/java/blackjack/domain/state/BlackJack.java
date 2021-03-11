package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackJack implements State {
    private final Cards cards;

    public BlackJack(final Cards cards) {
        this.cards = cards;
    }
}
