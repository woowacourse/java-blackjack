package blackjack.model;

public enum ResultStatus {

    LOSE("패", "승"),
    PUSH("무", "무"),
    WIN("승", "패");

    private final String playerResult;
    private final String dealerResult;

    ResultStatus(String playerResult, String dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public String getPlayerResult() {
        return playerResult;
    }

    public String getDealerResult() {
        return dealerResult;
    }
}
