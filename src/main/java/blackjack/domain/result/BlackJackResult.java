package blackjack.domain.result;

public enum BlackJackResult {

    BLACKJACK_WIN("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String koreanName;
    private final double profitRate;

    BlackJackResult(String koreanName, double profitRate) {
        this.koreanName = koreanName;
        this.profitRate = profitRate;
    }

    public BlackJackResult opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public double getProfitRate() {
        return profitRate;
    }
}