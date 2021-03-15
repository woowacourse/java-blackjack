package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State takeCard(Card card) {
        cards.takeCard(card);
        final Score score = calculateScore();

        if (score.isBurst()) {
            return new Burst(cards);
        }
        if (score.isBlackjack()) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }



}
