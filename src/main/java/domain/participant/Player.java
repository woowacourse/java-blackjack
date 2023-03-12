package domain.participant;

import domain.betting.PlayerBettingResult;

public class Player extends Participant {

    private static final int LIMIT_HIT_VALUE = 21;

    public Player(Name name, Hand hand) {
        super(name, hand);
    }

    public boolean canHit() {
        return this.calculateOptimalCardValueSum() <= LIMIT_HIT_VALUE;
    }

    public PlayerBettingResult calculateBettingResult(Hand dealerHand) {
        if ((dealerHand.isBlackjack() || hand.isBust()) ||
                (hand.isStay() && dealerHand.isStay() && isLoseWhenStay(dealerHand))) {
            return PlayerBettingResult.LOSE;
        }
        if ((hand.isStay() && dealerHand.isBust()) ||
                (hand.isStay() && dealerHand.isStay() && isWinWhenStay(dealerHand))) {
            return PlayerBettingResult.WIN;
        }
        return PlayerBettingResult.BLACKJACK;
    }

    private boolean isWinWhenStay(Hand dealerHand) {
        return hand.calculateOptimalCardValueSum() >= dealerHand.calculateOptimalCardValueSum();
    }

    private boolean isLoseWhenStay(Hand dealerHand) {
        return hand.calculateOptimalCardValueSum() < dealerHand.calculateOptimalCardValueSum();
    }
}
