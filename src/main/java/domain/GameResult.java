package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String resultKoreanName;

    GameResult(String resultKoreanName) {
        this.resultKoreanName = resultKoreanName;
    }

    public String getResultKoreanName() {
        return resultKoreanName;
    }
}
