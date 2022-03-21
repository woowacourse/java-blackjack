package blackjack.domain.participant.playerstatus;

public final class Blackjack extends CalculableStatus {

    private static final Blackjack INSTANCE = new Blackjack();
    private static final double WIN_EARNING_RATE = 1.5;
    private static final int PUSH_EARNING_RATE = 0;

    private Blackjack() {
    }

    public static Blackjack getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculateProfit(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                                  final double bettingAmount) {
        if (dealerBlackjack) {
            return bettingAmount * PUSH_EARNING_RATE;
        }
        return bettingAmount * WIN_EARNING_RATE;
    }
}
