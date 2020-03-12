package blackjack.domain;

public enum UserResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String koreanName;

    UserResult(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
