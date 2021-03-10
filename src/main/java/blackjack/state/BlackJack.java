package blackjack.state;

import blackjack.domain.card.Card;

import java.util.List;

public class BlackJack implements State{

    private List<Card> cards;

    public BlackJack(final List<Card> cards) {
        this.cards = cards;
    }
}
