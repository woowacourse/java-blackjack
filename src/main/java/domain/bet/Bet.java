package domain.bet;

import java.util.Objects;

public class Bet {
    private static final int BLACKJACK_PRIZE_RATE = 15;
    private static final int DRAW_BET_PRIZE = 0;

    private final int bettingAmount;

    public Bet(int bettingAmount) {
        validateBetAmountRange(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateBetAmountRange(int betAmount){
        if(betAmount <= 0){
            throw new IllegalArgumentException("[ERROR] 배팅 금액이 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return bettingAmount == bet.bettingAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bettingAmount);
    }

    public int getBlackJackPrize() {
        return bettingAmount * BLACKJACK_PRIZE_RATE / 10;
    }

    public int getWinningPrize() {
        return bettingAmount;
    }

    public int getLosingPrize() {
        return -bettingAmount;
    }

    public int getDrawPrize() {
        return DRAW_BET_PRIZE;
    }
}
