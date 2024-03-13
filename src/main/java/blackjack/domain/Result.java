package blackjack.domain;

public enum Result {

    BLACK_JACK("승"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int DRAW_PROFIT = 0;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result reverseResult(Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    //TODO: TEST 작성하기
    public int getBetProfit(int betMoney) {
        if (this == BLACK_JACK) {
            return (int) (1.5 * betMoney);
        }
        if (this == WIN) {
            return betMoney;
        }
        if (this == LOSE) {
            return -betMoney;
        }
        if (this == DRAW) {
            return DRAW_PROFIT;
        }
        throw new IllegalStateException("존재하지 않는 결과입니다.");
    }

    public String getResult() {
        return result;
    }
}
