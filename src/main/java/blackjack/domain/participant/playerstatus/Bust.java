package blackjack.domain.participant.playerstatus;

import blackjack.domain.prizecalculator.BustCalculator;
import blackjack.domain.prizecalculator.PrizeCalculator;

public final class Bust extends CalculableStatus {

    private static final Bust INSTANCE = new Bust();

    private Bust() {
    }

    public static Bust getInstance() {
        return INSTANCE;
    }

    @Override
    public PrizeCalculator findCalculator() {
        return BustCalculator.getInstance();
    }
}
