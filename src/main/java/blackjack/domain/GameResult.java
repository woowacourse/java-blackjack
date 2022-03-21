package blackjack.domain;

public enum GameResult {
    WIN_BLACKJACK("승", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String result;
    private final double prizeRate;

    GameResult(String result, double prizeRate) {
        this.result = result;
        this.prizeRate = prizeRate;
    }

    public int calculateRevenue(int bettingMoney) {
        return (int) (bettingMoney  * prizeRate);
    }

}
