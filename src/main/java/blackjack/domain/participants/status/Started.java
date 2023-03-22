package blackjack.domain.participants.status;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;

import java.util.List;

public abstract class Started implements Status {

    protected final Hand cards;

    protected Started(Hand cards) {
        this.cards = cards;
    }

    @Override
    public Score score() {
        return cards.getScore();
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

}
