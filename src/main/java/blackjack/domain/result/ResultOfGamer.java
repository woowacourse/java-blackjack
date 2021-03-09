package blackjack.domain.result;

import blackjack.domain.WinOrLose;

public class ResultOfGamer {
    private final String name;
    private final WinOrLose winOrLose;
    private final long revenue;

    public ResultOfGamer(String name, WinOrLose winOrLose, long revenue) {
        this.name = name;
        this.winOrLose = winOrLose;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public WinOrLose getWinOrLose() {
        return winOrLose;
    }

    public String getWinOrLoseAsString() {
        return winOrLose.getMessage();
    }

    public long getRevenue() {
        return revenue;
    }
}
