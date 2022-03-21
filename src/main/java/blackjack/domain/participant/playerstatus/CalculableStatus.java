package blackjack.domain.participant.playerstatus;

public abstract class CalculableStatus implements PlayerStatus {

    @Override
    public boolean isRunning() {
        return false;
    }

    public abstract double calculateProfit(int playerScore, int dealerScore, boolean dealerBlackjack,
                                           double bettingAmount);
}
