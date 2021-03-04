package blackjack.domain.user;

public enum OneGameResult {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String gameKoreanResult;

    OneGameResult(String gameKoreanResult) {
        this.gameKoreanResult = gameKoreanResult;
    }

    public String getResult() {
        return this.gameKoreanResult;
    }
}
