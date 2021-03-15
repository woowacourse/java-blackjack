package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public abstract class Finished implements State {
    protected Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Score calculateScore() {
        return cards.sumCardsForResult();
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
