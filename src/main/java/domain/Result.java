package domain;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String koreanText;

    Result(String koreanText) {
        this.koreanText = koreanText;
    }

    public String getKoreanText() {
        return koreanText;
    }
}
