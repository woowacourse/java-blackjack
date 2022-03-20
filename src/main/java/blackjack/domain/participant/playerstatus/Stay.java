package blackjack.domain.participant.playerstatus;

import blackjack.domain.prizecalculator.PrizeCalculator;
import blackjack.domain.prizecalculator.StayCalculator;

public final class Stay extends CalculableStatus {

    private static final Stay INSTANCE = new Stay();

    private Stay() {
    }

    public static Stay getInstance() {
        return INSTANCE;
    }

    @Override
    public PrizeCalculator findCalculator() {
        return StayCalculator.getInstance();
    }
}
