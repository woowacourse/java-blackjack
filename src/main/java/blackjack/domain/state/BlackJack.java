package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackJack implements State {
    private final Cards cards;

    public BlackJack(Cards cards) {
        this.cards = cards;
    }
}
