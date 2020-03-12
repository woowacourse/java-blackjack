package blackjack.domain.result;

public enum BlackJackResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String koreanName;

    BlackJackResult(String koreanName) {
        this.koreanName = koreanName;
    }

    public BlackJackResult reversed() {
        if (this == WIN)
            return LOSE;
        if (this == LOSE)
            return WIN;
        return DRAW;
    }

    public String getKoreanName() {
        return koreanName;
    }
}