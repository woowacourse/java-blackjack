package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {
    protected Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isWin(State state) {
        return cards.isWin(state.getCards());
    }

    @Override
    public boolean isDraw(State state) {
        return cards.isDraw(state.getCards());
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
