package domain.player;

public enum WinStatus {
    WIN("승", 1.0),
    DRAW("무", 0),
    LOSE("패", -1.0),
    BLACKJACK_WIN("승", 1.5);

    private final String status;
    private final double earningsRate;

    WinStatus(String status, double earningsRate) {
        this.status = status;
        this.earningsRate = earningsRate;
    }

    public String getStatus() {
        return status;
    }

    public double getEarningsRate() {
        return earningsRate;
    }
}
