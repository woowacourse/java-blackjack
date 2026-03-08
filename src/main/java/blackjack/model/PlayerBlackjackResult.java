package blackjack.model;

public enum PlayerBlackjackResult {
    WIN("승"),
    LOSE("패"),
    PUSH("푸시");

    private final String label;

    PlayerBlackjackResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
