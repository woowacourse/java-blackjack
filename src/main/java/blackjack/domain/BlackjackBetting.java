package blackjack.domain;

public final class BlackjackBetting {
    
    private static final int MIN_BETTING_AMOUNT = 1000;
    private static final int MAX_BETTING_AMOUNT = 100000;
    private static final int BETTING_UNIT = 1000;
    private static final double BLACK_JACK_PROFIT_RATIO = 1.5;
    
    private final int bettingAmount;
    
    public BlackjackBetting(final int bettingAmount) {
        validateBettingAmount(bettingAmount);
        validateBettingUnit(bettingAmount);
        this.bettingAmount = bettingAmount;
    }
    
    private void validateBettingAmount(final int bettingAmount) {
        if (bettingAmount < MIN_BETTING_AMOUNT || bettingAmount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 %d원 이상, %d원 이하여야 합니다.".formatted(MIN_BETTING_AMOUNT, MAX_BETTING_AMOUNT));
        }
    }
    
    private void validateBettingUnit(final int bettingAmount) {
        if (bettingAmount % BETTING_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 %d원 단위여야 합니다.".formatted(BETTING_UNIT));
        }
    }
    
    public double calculateIncome(final WinningStatus winningStatus, final boolean isBlackjackWinning) {
        if (isBlackjackWinning) {
            return bettingAmount * BLACK_JACK_PROFIT_RATIO;
        }
        return switch (winningStatus) {
            case 플레이어_승리 -> bettingAmount;
            case 무승부 -> 0;
            case 플레이어_패배 -> -bettingAmount;
        };
    }
}
