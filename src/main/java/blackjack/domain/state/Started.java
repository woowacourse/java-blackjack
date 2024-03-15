package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Score;

public abstract class Started implements State {
    private final CardHand cardHand;

    Started(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public CardHand receiveCard(Card card) {
        cardHand.receiveCard(card);
        return cardHand;
    }

    @Override
    public Score calculateScore() {
        return cardHand.sumAllScore();
    }

    @Override
    public CardHand getCardHand() {
        return cardHand;
    }
}
