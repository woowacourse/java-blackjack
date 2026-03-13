package blackjack.domain.participant;

public class Player extends Participant {

    private final int bettingAmount;
    private boolean stopDrawing;

    public Player(String nickname, int bettingAmount) {
        super(nickname);
        validateBettingAmount(bettingAmount);
        this.stopDrawing = false;
        this.bettingAmount = bettingAmount;
    }

    private void validateBettingAmount(int bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("배팅 금액은 1 이상의 양수여야 합니다.");
        }
        if (bettingAmount == 0) {
            throw new IllegalArgumentException("배팅 금액은 0일 수 없습니다.");
        }
    }

    @Override
    public boolean isDrawable() {
        return !stopDrawing && super.isDrawable();
    }

    public void stand() {
        stopDrawing = true;
    }

    public int calculateProfit(ProfitRate profitRate) {
        return profitRate.getProfit(bettingAmount);
    }
}
