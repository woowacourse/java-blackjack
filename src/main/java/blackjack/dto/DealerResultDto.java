package blackjack.dto;

public class DealerResultDto {

    private final String gameResult;
    private final int count;

    private DealerResultDto(String gameResult, int count) {
        this.gameResult = gameResult;
        this.count = count;
    }

    public static DealerResultDto of(String gameResult, int count) {
        return new DealerResultDto(gameResult, count);
    }

    public String getGameResult() {
        return gameResult;
    }

    public int getCount() {
        return count;
    }
}
