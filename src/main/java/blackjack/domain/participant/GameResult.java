package blackjack.domain.participant;

public class GameResult {
    private int winCount;
    private int drawCount;
    private int loseCount;

    public GameResult() {
    }

    public void win() {
        winCount += 1;
    }

    public void draw() {
        drawCount += 1;
    }

    public void lose() {
        loseCount += 1;
    }

    public void plus(GameResult gameResult) {
        winCount += gameResult.getWinCount();
        drawCount += gameResult.getDrawCount();
        loseCount += gameResult.getLoseCount();
    }

    public GameResult reverse() {
        GameResult reversedGameResult = new GameResult();
        reversedGameResult.winCount = loseCount;
        reversedGameResult.drawCount = drawCount;
        reversedGameResult.loseCount = winCount;

        return reversedGameResult;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
