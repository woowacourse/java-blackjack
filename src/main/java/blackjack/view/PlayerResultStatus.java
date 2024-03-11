package blackjack.view;

public enum PlayerResultStatus {

    LOSE("패"),
    PUSH("무"),
    WIN("승");

    private final String result;

    PlayerResultStatus(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
