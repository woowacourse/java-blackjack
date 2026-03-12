package model;

public enum MatchStatus {
    BLACKJACK("블랙잭 승", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0);

    private final String status;
    private final double multiplier;

    MatchStatus(String status, double multiplier) {
        this.status = status;
        this.multiplier = multiplier;
    }

    public String getStatus() {
        return status;
    }

    public double getMultiplier() {
        return multiplier;
    }

}
