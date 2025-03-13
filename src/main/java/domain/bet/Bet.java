package domain.bet;

import java.util.Objects;

public class Bet {
    private static final int BLACKJACK_PRIZE_RATE = 15;
    private static final int DRAW_BET_PRIZE = 0;

    int bettingCount;

    public Bet(int bettingCount) {
        this.bettingCount = bettingCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return bettingCount == bet.bettingCount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bettingCount);
    }

    public int getBlackJackPrize(){
        return bettingCount * BLACKJACK_PRIZE_RATE / 10;
    }

    public int getWinningPrize(){
        return bettingCount;
    }

    public int getLosingPrize(){
        return -bettingCount;
    }

    public int getDrawPrize(){
        return DRAW_BET_PRIZE;
    }
}
