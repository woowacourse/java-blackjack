package blackjack.domain;

public enum PlayerResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String koreanName;

    PlayerResult(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
