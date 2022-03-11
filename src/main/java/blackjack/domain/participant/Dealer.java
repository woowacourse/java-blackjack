package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;

public class Dealer extends Participant {

    private static final int SCORE_LOWER_BOUND = 17;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public CardCount drawCards(CardFactory cardFactory) {
        int drawCount = 0;
        while (getStatus() == Status.HIT && getScore() < SCORE_LOWER_BOUND) {
            hit(cardFactory);
            drawCount++;
        }

        return CardCount.of(drawCount);
    }

    public Card openFirstCard() {
        return getCards().findFirst();
    }
}
