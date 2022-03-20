package blackjack.domain.participant.playerstatus;

import blackjack.domain.prizecalculator.BlackjackCalculator;
import blackjack.domain.prizecalculator.PrizeCalculator;

public final class Blackjack extends CalculableStatus {

    private static final Blackjack INSTANCE = new Blackjack();

    private Blackjack() {
    }

    public static Blackjack getInstance() {
        return INSTANCE;
    }

    @Override
    public PrizeCalculator findCalculator() {
        return BlackjackCalculator.getInstance();
    }
}
