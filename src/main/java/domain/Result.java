package domain;

public enum Result {
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String koreanText;
    private final double odds;

    Result(String koreanText, double odds) {
        this.koreanText = koreanText;
        this.odds = odds;
    }

    public String getKoreanText() {
        return koreanText;
    }

    public int calculateWinningAmount(int bettingAmountValue) {
        return (int) odds * bettingAmountValue;
    }
}
