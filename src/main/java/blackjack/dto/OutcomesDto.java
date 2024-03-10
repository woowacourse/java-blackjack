package blackjack.dto;

public class OutcomesDto {

    private final int winCount;
    private final int loseCount;
    private final int pushCount;

    public OutcomesDto(final int winCount, final int loseCount, final int pushCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.pushCount = pushCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getPushCount() {
        return pushCount;
    }
}
