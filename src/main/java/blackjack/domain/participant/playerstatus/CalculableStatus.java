package blackjack.domain.participant.playerstatus;

import blackjack.domain.prizecalculator.PrizeCalculator;

public abstract class CalculableStatus implements PlayerStatus {

    @Override
    public boolean isRunning() {
        return false;
    }

    public abstract PrizeCalculator findCalculator();
}
