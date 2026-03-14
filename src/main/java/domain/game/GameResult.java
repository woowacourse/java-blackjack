package domain.game;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    BLACKJACK("블랙잭");

    private final String resultKoreanName;

    GameResult(String resultKoreanName) {
        this.resultKoreanName = resultKoreanName;
    }

    public String getResultKoreanName() {
        return resultKoreanName;
    }
}
