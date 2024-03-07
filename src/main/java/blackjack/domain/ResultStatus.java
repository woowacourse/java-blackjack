package blackjack.domain;

public class ResultStatus {

    private int wins;
    private int loses;
    private int draws;

    public ResultStatus(final int wins, final int loses, final int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public void setResultStatus(final int wins, final int loses, final int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
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
