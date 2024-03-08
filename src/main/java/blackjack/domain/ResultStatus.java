package blackjack.domain;

import blackjack.dto.DealerResult;

public class ResultStatus {

    private int wins;
    private int loses;
    private int draws;

    private ResultStatus(final int wins, final int loses, final int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public static ResultStatus init() {
        return new ResultStatus(0, 0, 0);
    }

    public void updateResultStatus(final DealerResult result) {
        wins = result.getWins();
        loses = result.getLoses();
        draws = result.getDraws();
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getDraws() {
        return draws;
    }
}
