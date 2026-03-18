package domain;

public enum WinningStatus {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    BLACKJACK_WIN("승", 1.5);

    private final String label;
    private final double payoutRatio;

    WinningStatus(String label, double payoutRatio) {
        this.label = label;
        this.payoutRatio = payoutRatio;
    }

    public String getLabel() {
        return label;
    }

    public double getPayoutRatio() {
        return payoutRatio;
    }
}
