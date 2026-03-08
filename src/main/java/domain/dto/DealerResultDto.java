package domain.dto;

public class DealerResultDto {

    private String name;
    private int winCount;
    private int drawCount;
    private int loseCount;

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
