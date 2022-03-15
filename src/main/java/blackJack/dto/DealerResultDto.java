package blackJack.dto;

import java.util.Map;

public class DealerResultDto {

    private final int winCount;
    private final int drawCount;
    private final int loseCount;

    private DealerResultDto(int winCount, int drawCount, int loseCount) {
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public static DealerResultDto from(Map<String, String> result) {
        int winCount = 0;
        int loseCount = 0;
        for (String value : result.values()) {
            winCount = getWinCount(winCount, value);
            loseCount = getLoseCount(loseCount, value);
        }
        int drawCount = result.size() - (winCount + loseCount);
        return new DealerResultDto(winCount, drawCount, loseCount);
    }

    private static int getLoseCount(int loseCount, String value) {
        if (value.equals("패")) {
            loseCount++;
        }
        return loseCount;
    }

    private static int getWinCount(int winCount, String value) {
        if (value.equals("승")) {
            winCount++;
        }
        return winCount;
    }
}
