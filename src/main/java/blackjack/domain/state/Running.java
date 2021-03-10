package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Running implements State {
    protected Cards cards;

    public Running(Cards cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
