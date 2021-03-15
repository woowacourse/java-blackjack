package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public abstract class Running implements State {
    protected final Cards cards;

    public Running(Cards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return false;
    }

    @Override
    public Score calculateScore() {
        return cards.sumCards();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

}
