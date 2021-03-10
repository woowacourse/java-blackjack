package blackjack.domain.result;

public enum BlackjackResult {

    BLACKJACK("블랙잭", 1.5),
    BUST("버스트", -1),
    WIN("승", 1),
    LOSE("패", -1),
    TIE("무", 0);

    private final String result;
    private final double earningRate;

    BlackjackResult(String result, double earningRate) {
        this.result = result;
        this.earningRate = earningRate;
    }

    public String getResult() {
        return result;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
