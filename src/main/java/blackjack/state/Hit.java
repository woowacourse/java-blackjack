package blackjack.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Hit extends Running {

    public Hit(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(Card card) {
        cardHand.add(card);
        if (cardHand.isBust()) {
            return new Bust(cardHand);
        }
        return new Hit(cardHand);
    }

}
