package domain;

public enum WinningStatus {
    WIN("승",1),
    DRAW("무",0),
    LOSE("패",-1),
    BLACKJACK_WIN("승",1.5);

    private final String description;
    private final double payoutRatio;

    WinningStatus(String description, double payoutRatio) {
        this.description = description;
        this.payoutRatio = payoutRatio;
    }

    public String getDescription() {
        return description;
    }

    public double getPayoutRatio() {
        return payoutRatio;
    }
}
