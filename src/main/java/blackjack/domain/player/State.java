package blackjack.domain.player;

public class State {

    private int winCount = 0;
    private int loseCount = 0;

    public void win() {
        winCount += 1;
    }

    public void lose() {
        loseCount += 1;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
