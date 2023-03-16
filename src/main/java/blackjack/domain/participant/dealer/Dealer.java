package blackjack.domain.participant.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;


    public Dealer() {
        super(new Name(DEALER_NAME), new Hand());
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }

    public boolean isUnderScore() {
        return hand.calculateScore().getValue() < DEALER_MIN_SCORE;
    }
}
