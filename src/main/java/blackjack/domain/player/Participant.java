package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.HitStrategy;

public class Participant {

    private static final int BLACKJACK = 21;

    private final Hand hand;
    private final ParticipantType participantType;

    public Participant(Hand hand, ParticipantType participantType) {
        this.hand = hand;
        this.participantType = participantType;
    }

    public void hit(Card card) {
        if (canHit()) {
            hand.append(card);
        }
    }

    private boolean canHit() {
        HitStrategy hitStrategy = participantType.getHitStrategy();
        return hitStrategy.canHit(calculateHandTotalClosestToBlackjack());
    }

    public boolean isBust() {
        return calculateHandTotalClosestToBlackjack() > BLACKJACK;
    }

    public int calculateHandTotalClosestToBlackjack() {
        return hand.calculateScoreTotalClosestToThreshold(BLACKJACK);
    }
}
