package dto;

import domain.Dealer;

public class DealerWinLoseResult {

    private final String name;
    private final int winCount;
    private final int loseCount;

    private DealerWinLoseResult(final String name, final int winCount, final int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static DealerWinLoseResult toDto(final Dealer dealer, final int winCount, final int loseCount) {
        return new DealerWinLoseResult(dealer.getName(), winCount, loseCount);
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
