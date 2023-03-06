package blackjack.dto;

public class DealerResultDto {

    private final String name;
    private final int winCount;
    private final int drawCount;
    private final int loseCount;

    public DealerResultDto(String name, int winCount, int drawCount, int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public String getName() {
        return name;
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
