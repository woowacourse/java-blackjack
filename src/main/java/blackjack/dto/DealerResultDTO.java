package blackjack.dto;

public class DealerResultDTO {

    private final String name;
    private final int winCount;
    private final int loseCount;

    public DealerResultDTO(String name, int winCount, int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
