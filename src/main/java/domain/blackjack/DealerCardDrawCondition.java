package domain.blackjack;

import domain.card.CardDrawCondition;

public final class DealerCardDrawCondition implements CardDrawCondition {
    public static final int RAW_DEALER_DRAW_THRESHOLD_POINT = 16;
    private final BlackJackGameMachine blackJackGameMachine;

    DealerCardDrawCondition(BlackJackGameMachine blackJackGameMachine) {
        this.blackJackGameMachine = blackJackGameMachine;
    }

    @Override
    public boolean canDraw() {
        SummationCardPoint dealerDrawThresholdPoint = new SummationCardPoint(RAW_DEALER_DRAW_THRESHOLD_POINT);

        SummationCardPoint summationCardPoint = blackJackGameMachine.calculateSummationCardPoint();
        return !summationCardPoint.isBiggerThan(dealerDrawThresholdPoint);
    }
}
