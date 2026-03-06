package blackjack.model;

public enum BlackjackResult {
    WIN("승"),
    LOSE("패"),
    PUSH("푸시");

    private final String label;

    BlackjackResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
