package domain.result;

import java.util.List;

public final class Result {

    private final List<String> winners;
    private final List<String> losers;

    public Result(final List<String> winners, final List<String> losers) {
        this.winners = winners;
        this.losers = losers;
    }

    public int countWinners() {
        return winners.size();
    }

    public int countLosers() {
        return losers.size();
    }

    public List<String> getWinners() {
        return List.copyOf(winners);
    }

    public List<String> getLosers() {
        return List.copyOf(losers);
    }
}
