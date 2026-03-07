package blackjack.dto;

public record WinningResult(
    String nickname,
    int winCount,
    int loseCount
) {

    public int getWinCountForResult() {
        if(winCount < 0) {
            return 0;
        }
        return winCount;
    }

    public int getLoseCountForResult() {
        if(loseCount < 0) {
            return 0;
        }
        return loseCount;
    }

    public String dealerWinningResult() {
        return String.format("%s: %d승 %d패", nickname, winCount, loseCount);
    }

    public String playerWinningResult() {
        if (winCount > 0) {
            return String.format("%s: 승", nickname);
        }
        return String.format("%s: 패", nickname);
    }
}
