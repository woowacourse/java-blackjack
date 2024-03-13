package domain.blackjack;

import domain.card.CardDrawCondition;

final class DealerCardDrawCondition implements CardDrawCondition {
    private final BlackJackGameMachine blackJackGameMachine;

    DealerCardDrawCondition(BlackJackGameMachine blackJackGameMachine) {
        this.blackJackGameMachine = blackJackGameMachine;
    }

    @Override
    public boolean canDraw() {
        final int rawDealerDrawThresholdPoint = 16;
        SummationCardPoint dealerDrawThresholdPoint = new SummationCardPoint(rawDealerDrawThresholdPoint);

        SummationCardPoint summationCardPoint = blackJackGameMachine.calculateSummationCardPoint();
        return !summationCardPoint.isBiggerThan(dealerDrawThresholdPoint);
    }
}
