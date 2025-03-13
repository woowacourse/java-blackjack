package domain.bet;

import java.util.Objects;

public class Bet {
    private final int BLACKJACK_PRIZE_RATE = 15;
    private final int DRAW_BET_PRIZE = 0;

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

    public Bet getBlackJackPrize(){
        return new Bet(bettingCount * BLACKJACK_PRIZE_RATE / 10);
    }

    public Bet getWinningPrize(){
        return new Bet(bettingCount);
    }

    public Bet getLosingPrize(){
        return new Bet(-bettingCount);
    }

    public Bet getDrawPrize(){
        return new Bet(DRAW_BET_PRIZE);
    }

    public int getBettingCount() {
        return bettingCount;
    }
}
