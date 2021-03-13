package blackjack.domain.state;

import blackjack.domain.carddeck.Card;

import java.util.List;

public abstract class Started implements State {

    protected final Hand hand;

    protected Started(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public List<Card> toHandList() {
        return hand.toList();
    }

    @Override
    public Score totalScore() {
        return hand.totalScore();
    }
}
