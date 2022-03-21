package blackjack.domain.participant.playerstatus;

public final class Bust extends CalculableStatus {

    private static final Bust INSTANCE = new Bust();
    private static final int EARNING_RATE = -1;

    private Bust() {
    }

    public static Bust getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculateProfit(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                                  final double bettingAmount) {
        return bettingAmount * EARNING_RATE;
    }
}
