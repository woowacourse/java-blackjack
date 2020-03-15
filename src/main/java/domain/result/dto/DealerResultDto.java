package domain.result.dto;

import domain.result.GameResults;

public class DealerResultDto {
    private int winCount;
    private int drawCount;
    private int loseCount;

    private DealerResultDto(int winCount, int drawCount, int loseCount) {
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public static DealerResultDto of(GameResults gameResults) {
        return new DealerResultDto(gameResults.countLose(), gameResults.countDraw(), gameResults.countWin());
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
