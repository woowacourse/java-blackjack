package blackjack.domain.result;

public enum BlackJackResult {

    BLACKJACK("블랙잭", 1.5d),
    WIN("승", 1d),
    DRAW("무", 0d),
    LOSE("패", -1d);

    private final String koreanName;
    private final double profitRate;

    BlackJackResult(String koreanName, double profitRate) {
        this.koreanName = koreanName;
        this.profitRate = profitRate;
    }

    public BlackJackResult reverse() {
        if (this == WIN)
            return LOSE;
        if (this == LOSE)
            return WIN;
        return DRAW;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public double getProfitRate() {
        return profitRate;
    }
}